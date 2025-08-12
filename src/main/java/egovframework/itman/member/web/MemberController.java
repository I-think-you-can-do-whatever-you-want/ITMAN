package egovframework.itman.member.web;

import egovframework.itman.member.service.impl.MemberServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class MemberController {
    @Resource(name = "memberService")
    private MemberServiceImpl memberService;

    @RequestMapping("/itman/user/join.do")
    public String join() {

        return "itman/public/html/user/join01";
    }

    @RequestMapping("/itman/user/writeUserInfo.do")
    public String writeUserInfo() {

        return "itman/public/html/user/join02";
    }

    @PostMapping("/itman/sendEmail.do")
    public String sendEmail() {
        return "";
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
}
