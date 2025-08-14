package egovframework.itman.member.web;

import egovframework.itman.member.service.MemberVO;
import egovframework.itman.member.service.impl.MemberServiceImpl;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

@Controller
public class MemberController {
    @Resource(name = "memberService")
    private MemberServiceImpl memberService;
    @Resource(name = "passwordEncoder")
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping("/itman/user/join.do")
    public String join() {
        return "itman/public/html/user/join01";
    }

    @RequestMapping("/itman/user/writeUserInfo.do")
    public String writeUserInfo() {
        return "itman/public/html/user/join02";
    }

    @PostMapping(value = "/itman/checkMail.do", produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String checkMail(@RequestParam("email") String email) {
        boolean valid = email != null && email.matches(
                "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        );
        if(!valid)
            return "2";
        //        반환값 의미: 0=사용 가능, 1=이미 존재, 2=형식 오류
       boolean exist = memberService.existsByEmail(email);

        return exist ? "1" : "0";

    }

    @RequestMapping(value = "/itman/sendMailCode.do", method = RequestMethod.POST)
//    @ResponseBody
    public String sendMailCode(@ModelAttribute MemberVO vo, @RequestParam("memMail") String email, HttpSession session) throws Exception {
        session.setAttribute("member", vo);
        String code = String.valueOf((int)((Math.random() * 900000) + 100000)); //6자리 랜덤 숫자
        memberService.sendAuthMail(email, code);
        session.setAttribute("authCode", code);

        return "itman/public/html/user/certPass";
    }

    @RequestMapping(value = "/itman/checkMailCode.do", method = RequestMethod.POST)
//    @ResponseBody
    public String checkMailCode(@RequestParam("inputCode") String inputCode, HttpSession session, Model model) {
        String savedCode = (String) session.getAttribute("authCode");
        model.addAttribute("authCode", savedCode);
        if(savedCode != null && savedCode.equals(inputCode)) {
            MemberVO vo = (MemberVO) session.getAttribute("member");

            String rawPassword = vo.getMemPw();
            String encodedPassword = passwordEncoder.encode(rawPassword);
            vo.setMemPw(encodedPassword);

            memberService.insertMember(vo);
            model.addAttribute("member", vo);

            session.removeAttribute("authCode");
            session.removeAttribute("member");
            return "itman/public/html/user/join03";
        } else {
            return "fail";
        }
    }

    @RequestMapping("/itman/user/login.do")
    public String login() {
        return "itman/public/html/user/login";
    }

    @PostMapping("/itman/user/authUser.do")
    public String authUser(@ModelAttribute MemberVO vo, HttpSession session,
    @RequestParam("inputMail") String inputMail,
    @RequestParam("inputPw") String inputPw ,
                           Model model) {
        MemberVO member = memberService.selectMemberByEmail(inputMail);
        if(member == null || !passwordEncoder.matches(inputPw, member.getMemPw())) {
            model.addAttribute("msg", "존재하지않는 회원이거나 아이디 또는 비밀번호가 일치하지않습니다.");
            return "itman/public/html/user/login";
        }
        session.setAttribute("loginUser", member);
        session.setAttribute("userIdx", member.getMemIdx());
        return "redirect:/itman/index.do";
    }

    @RequestMapping("/itman/myPage.do")
    public String myPage(HttpSession session, Model model) {
        String userMail = ((MemberVO) session.getAttribute("loginUser")).getMemMail();
        MemberVO vo = memberService.selectMemberByEmail(userMail);
        model.addAttribute("member", vo);
        session.setAttribute("member", vo);
        return "itman/public/html/user/mypage";
    }
    @RequestMapping("/itman/phoneEdit.do")
    public String phoneEdit(HttpSession session, Model model) {
        MemberVO vo = (MemberVO) session.getAttribute("member");
        model.addAttribute("memTel", vo.getMemTel());
        return "itman/public/html/popup/phoneEdit";
    }
    @PostMapping("/itman/updatePhone.do")
    public String updatePhone(@RequestParam("memTel") String memTel,HttpSession session, Model model) {
        MemberVO vo = (MemberVO) session.getAttribute("member");
        session.removeAttribute("member");
        vo.setMemTel(memTel);
        memberService.updateMemTel(vo);
        session.setAttribute("member", vo);
        model.addAttribute("script", "<script>window.opener.location.reload(); window.close();</script>");
        return "itman/common/scriptResponse";
    }

    @PostMapping(value = "/itman/authPassword.do", produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String updatePassword(@RequestParam("oldPw") String oldPw,
                                  HttpSession session, Model model) {
        MemberVO vo = (MemberVO) session.getAttribute("member");
        if(oldPw != null && passwordEncoder.matches(oldPw, vo.getMemPw())) {
            return "1";
        }

        return "0";
    }


    @RequestMapping("/itman/changePass.do")
    public String changePass() {
        return "itman/public/html/user/changePass";
    }

    @PostMapping("/itman/updatePass.do")
    public String updatePass(@RequestParam("newPw") String newPw, HttpSession session) {
        MemberVO vo = (MemberVO) session.getAttribute("member");
        String encodedPassword = passwordEncoder.encode(newPw);
        vo.setMemPw(encodedPassword);
        session.removeAttribute("member");
        memberService.updateMemPw(vo);
        session.setAttribute("member", vo);
        return "redirect:/itman/myPage.do";
    }


    @GetMapping("/itman/logout.do")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 비우기
        return "redirect:/itman/index.do";
    }


}
