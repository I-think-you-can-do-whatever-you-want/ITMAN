package egovframework.itman.share.web;

import egovframework.itman.group.service.GroupService;
import egovframework.itman.group.service.GroupVO;
import egovframework.itman.share.shareInvite.service.ShareInviteService;
import egovframework.itman.share.shareInvite.service.ShareInviteVO;
import egovframework.usr.com.EgovframeworkCommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShareController {
    @Resource
    private GroupService groupService;
    @Resource
    private ShareInviteService shareInviteService;

    @RequestMapping("/shareBoard.do")
    public String shareBoard(Model model) {
        return "inGroup/shareBoard";
    }

    //나의 공유
    @RequestMapping("/myShareHistory.do")
    public String myShareHistory(Model model) {
        return "inGroup/myShareHistory";
    }

    @RequestMapping("/myShareGroup.do")
    public String myShareGroup(ShareInviteVO shareInviteVO ,Model model, HttpSession session){
        String memIdx = (String) session.getAttribute("userIdx");
        shareInviteVO.setRegIdx(memIdx);
        List<ShareInviteVO> mySharedGroupList = shareInviteService.selectMyShareGroupList(shareInviteVO);
        int mySharedGroupListCnt = shareInviteService.selectMyShareGroupListCnt(shareInviteVO);
        System.err.println("mySharedGroupListCnt = " + mySharedGroupListCnt);

        model.addAttribute("mySharedGroupList", mySharedGroupList);
        model.addAttribute("mySharedGroupListCnt", mySharedGroupListCnt);
        return "inGroup/myShareGroup";
    }


    //내가 공유 받은
    @RequestMapping("/sharedHistory.do")
    public String sharedHistory(Model model) {
        return "inGroup/sharedHistory";
    }

    @RequestMapping("/sharedGroup.do")
    public String sharedGroup(Model model) {
        return "inGroup/sharedGroup";
    }

    //공유 그룹 추가
    @RequestMapping("/writeGroupShare.do")
    public String writeGroupShare(HttpSession session, Model model) {
        String memIdx = session.getAttribute("userIdx").toString();
        List<GroupVO> groupList = groupService.selectGroupListNotShare(memIdx);
        model.addAttribute("groupList", groupList);
        return "popup/group/writeGroupShare";
    }
    @RequestMapping("/createInviteCode.do")
    @ResponseBody
    public String createInviteCode() throws Exception {
        String code = shareInviteService.generateUniqueInviteCode(10);
        return code;
    }

    @PostMapping("/insertSharedGroup.do")
    public String insertSharedGroup(ShareInviteVO shareInviteVO, HttpSession session ,Model model) {
        shareInviteVO.setRegIdx(session.getAttribute("userIdx").toString());
        shareInviteService.insertShareInvite(shareInviteVO);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "공유 그룹이 추가되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }
    //공유 그룹 내용 수정
    @RequestMapping("/editSharedGroupPermission.do")
    public String editSharedGroupPermission(Model model) {
        return "popup/group/editSharedGroupPermission";
    }

    @PostMapping("/updateSharedGroupPermission.do")
    public String updateSharedGroupPermission(Model model) {
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "공유 정보가 수정되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }
    //권한 회수
    @RequestMapping("/confirmSharedGroupPermissionRevoke.do")
    public String confirmSharedGroupPermissionRevoke(Model model) {
        return "popup/group/confirmSharedGroupDel";
    }

    @PostMapping("/revokeSharedGroupPermission.do")
    public String revokeSharedGroupPermission(Model model) {
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "공유 권한이 회수되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }
    //공유 요청 승인
    @RequestMapping("/confirmSharedGroupApprove.do")
    public String confirmSharedGroupApprove(Model model) {
        return "popup/group/confirmSharedGroupDel";
    }

    @PostMapping("/approveSharedGroup.do")
    public String approveSharedGroup(Model model) {
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "공유 요청이 승인되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }
    //공유 요청 거절
    @RequestMapping("/confirmSharedGroupReject.do")
    public String confirmSharedGroupReject(Model model) {
        return "popup/group/confirmSharedGroupDel";
    }

    @PostMapping("/rejectSharedGroup.do")
    public String rejectSharedGroup(Model model) {
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "공유 요청이 거절되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }
    //공유 그룹 삭제
    @RequestMapping("/confirmSharedGroupDel.do")
    public String confirmSharedGroupDel(Model model) {
        return "popup/group/confirmSharedGroupDel";
    }

    @PostMapping("/deleteSharedGroup.do")
    public String deleteSharedGroup(Model model) {
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "공유 그룹이 삭제되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }
}

