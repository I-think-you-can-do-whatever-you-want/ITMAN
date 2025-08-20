package egovframework.itman.group.web;

import egovframework.itman.group.service.GroupVO;
import egovframework.itman.group.service.impl.GroupServiceImpl;
import egovframework.itman.member.service.MemberVO;
import egovframework.usr.com.EgovframeworkCommonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
public class GroupController {
    private final GroupServiceImpl groupService;

    public GroupController(GroupServiceImpl groupService) {
        this.groupService = groupService;
    }

    @RequestMapping("/index.do")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/group.do")
    public String selectGroupList(HttpSession session, Model model) throws Exception {
        session.removeAttribute("groIdx");
        session.removeAttribute("group");
        String memIdx = ((MemberVO) session.getAttribute("loginUser")).getMemIdx();
       List<GroupVO> list = groupService.selectGroupList(memIdx);

       model.addAttribute("groupList", list);
        return "group";
    }
    @RequestMapping("/addGroup.do")
    public String addGroup(GroupVO vo, Model model) {
        model.addAttribute("group", vo);
        return "popup/addGroup";
    }

    @PostMapping("/insertGroup.do")
    public String insertGroup(GroupVO vo,
                              @RequestParam(value = "groImgFile" , required = false) MultipartFile file,
                              HttpServletRequest request,
                              HttpSession session
            , Model model) throws Exception {
        if(!file.isEmpty()){
            String uploadDir = "/upload/groImg/";
            String realDir = request.getServletContext().getRealPath(uploadDir);
            File dir = new File(realDir);
            if(!dir.exists()) {
                dir.mkdirs();
            }

            String originalFileName = file.getOriginalFilename();
            String ext = (originalFileName != null && originalFileName.lastIndexOf('.') != -1) ? originalFileName.substring(originalFileName.lastIndexOf('.')) : "";
            String prefix = "groupImg";
            String datePart = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
            String randomPart = java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
            String savedName = prefix + "_" + datePart + "_" + randomPart + ext;
            File img = new File(dir, savedName);
            file.transferTo(img);
            vo.setGroImg(savedName);
        }
        String ip = session.getAttribute("userIp").toString();
        vo.setRegIp(ip);
        groupService.insertGroup(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "그룹이 추가되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

    @PostMapping("/setGroIdx.do")
    @ResponseBody
    public ResponseEntity<String> setSessionValue(@RequestParam("groIdx") String groIdx, HttpSession session) {
        session.setAttribute("groIdx", groIdx);
        GroupVO vo = groupService.selectGroup(groIdx);
        session.setAttribute("group", vo);
        return ResponseEntity.ok("success");
    }
    @RequestMapping("/myGroup.do")
    public String myGroup(HttpSession session, Model model) throws Exception {
        MemberVO member = (MemberVO) session.getAttribute("member");
        List<GroupVO> list = groupService.getAllGroupData(member.getMemIdx());
        model.addAttribute("resultList", list);
        return "user/myGroup";
    }

    @RequestMapping("/editGroup.do")
    public String editGroup(GroupVO vo, Model model) {
        GroupVO group = groupService.selectGroup(vo.getGroIdx());
        model.addAttribute("group", group);
        return "user/groupWrite";
    }
    @PostMapping("/updateGroup.do")
    public String updateGroup(GroupVO vo, HttpSession session, Model model,
                              @RequestParam(value = "groImgFile" , required = false) MultipartFile file,
                              HttpServletRequest request) throws Exception {
        vo.setGroImg(groupService.selectGroup(vo.getGroIdx()).getGroImg());
        if(!file.isEmpty()){
            System.err.println("!file.isEmpty");
            System.err.println(file.getOriginalFilename());
            String uploadDir = "/upload/groImg/";
            String realDir = request.getServletContext().getRealPath(uploadDir);
            File dir = new File(realDir);
            if(!dir.exists()) {
                dir.mkdirs();
            }

            String originalFileName = file.getOriginalFilename();
            String ext = (originalFileName != null && originalFileName.lastIndexOf('.') != -1) ? originalFileName.substring(originalFileName.lastIndexOf('.')) : "";
            String prefix = "groupImg";
            String datePart = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
            String randomPart = java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
            String savedName = prefix + "_" + datePart + "_" + randomPart + ext;
            File img = new File(dir, savedName);
            file.transferTo(img);
            vo.setGroImg(savedName);
        }
        String ip = session.getAttribute("userIp").toString();
        vo.setModIp(ip);
        groupService.updateGroup(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "그룹이 수정되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

    @RequestMapping("/confirmGroupDel.do")
    public String confirmGroupDel(GroupVO vo, Model model) {
        GroupVO selectedVO = groupService.selectGroup(vo.getGroIdx());
        model.addAttribute("group", selectedVO);
        return "user/groupDel";
    }

    @PostMapping("/deleteGroup.do")
    public String deleteGroup(GroupVO vo, Model model, HttpSession session) throws Exception {
        String ip = session.getAttribute("userIp").toString();
        vo.setDelIp(ip);
        groupService.deleteGroup(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "그룹이 삭제되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

}
