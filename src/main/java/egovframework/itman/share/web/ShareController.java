package egovframework.itman.share.web;

import egovframework.itman.group.service.GroupService;
import egovframework.itman.group.service.GroupVO;
import egovframework.itman.share.shareHistory.service.ShareHistoryService;
import egovframework.itman.share.shareHistory.service.ShareHistoryVO;
import egovframework.itman.share.shareInvite.service.ShareInviteService;
import egovframework.itman.share.shareInvite.service.ShareInviteVO;
import egovframework.itman.share.sharePermission.service.SharePermissionService;
import egovframework.itman.share.sharePermission.service.SharePermissionVO;
import egovframework.itman.share.shareRequest.service.ShareRequestService;
import egovframework.itman.share.shareRequest.service.ShareRequestVO;
import egovframework.usr.com.EgovframeworkCommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShareController {
    @Resource
    private GroupService groupService;
    @Resource
    private ShareInviteService shareInviteService;
    @Resource
    private SharePermissionService sharePermissionService;
    @Resource
    private ShareRequestService shareRequestService;
    @Resource
    private ShareHistoryService shareHistoryService;

    @RequestMapping("/shareBoard.do")
    public String shareBoard(@RequestParam(value = "id",defaultValue = "10")int id, HttpSession session,Model model,
                             ShareInviteVO shareInviteVO, SharePermissionVO sharePermissionVO) {
        model.addAttribute("pageNumDepth01", id);
        String memIdx = (String) session.getAttribute("userIdx");

        shareInviteVO.setRegIdx(memIdx);
        List<ShareInviteVO> mySharedGroupList = shareInviteService.selectMyShareGroupList(shareInviteVO);
        model.addAttribute("mySharedGroupList", mySharedGroupList);
        sharePermissionVO.setOwnerMemIdx(memIdx);
        List<SharePermissionVO> mySharedPermissionList = sharePermissionService.selectMySharedPermissionList(sharePermissionVO);
        model.addAttribute("mySharedPermissionList", mySharedPermissionList);

        List<ShareHistoryVO> myShareHistoryList = shareHistoryService.selectMyShareHistory(memIdx);
        model.addAttribute("myShareHistoryList", myShareHistoryList);
        List<ShareHistoryVO> sharedHistoryList = shareHistoryService.selectSharedHistory(memIdx);
        model.addAttribute("sharedHistoryList", sharedHistoryList);
        return "inGroup/shareBoard";
    }

    @RequestMapping("/myShareGroup.do")
    public String myShareGroup(ShareInviteVO shareInviteVO, SharePermissionVO sharePermissionVO, Model model, HttpSession session){
        String memIdx = (String) session.getAttribute("userIdx");
        //나의 공유 그룹 조회
        shareInviteVO.setRegIdx(memIdx);
        List<ShareInviteVO> mySharedGroupList = shareInviteService.selectMyShareGroupList(shareInviteVO);
        int mySharedGroupListCnt = shareInviteService.selectMyShareGroupListCnt(shareInviteVO);
        model.addAttribute("mySharedGroupList", mySharedGroupList);
        model.addAttribute("mySharedGroupListCnt", mySharedGroupListCnt);
        //그룹 공유 현황
        sharePermissionVO.setOwnerMemIdx(memIdx);
        List<SharePermissionVO> mySharedPermissionList = sharePermissionService.selectMySharedPermissionList(sharePermissionVO);
        int mySharedPermissionListCnt = sharePermissionService.selectMySharedPermissionListCnt(sharePermissionVO);
        model.addAttribute("mySharedPermissionList", mySharedPermissionList);
        model.addAttribute("mySharedPermissionListCnt", mySharedPermissionListCnt);
        //받은 요청 현황
        List<ShareRequestVO> receivedRequestList = shareRequestService.selectReceivedRequestList(memIdx);
        int receivedRequestListCnt = shareRequestService.selectReceivedRequestListCnt(memIdx);
        model.addAttribute("receivedRequestList", receivedRequestList);
        model.addAttribute("receivedRequestListCnt", receivedRequestListCnt);
        return "inGroup/myShareGroup";
    }

    @RequestMapping("/sharedGroup.do")
    public String sharedGroup(SharePermissionVO sharePermissionVO,ShareRequestVO shareRequestVO ,HttpSession session ,Model model) {
        String memIdx = (String) session.getAttribute("userIdx");
        sharePermissionVO.setTargetMemIdx(memIdx);
        List<SharePermissionVO> sharedGroupList = sharePermissionService.selectSharedPermissionList(sharePermissionVO);
        int sharedGroupListCnt = sharePermissionService.selectSharedPermissionListCnt(sharePermissionVO);
        model.addAttribute("sharedGroupList", sharedGroupList);
        model.addAttribute("sharedGroupListCnt", sharedGroupListCnt);

        List<ShareRequestVO> selectRequestList = shareRequestService.selectRequestList(memIdx);
        int selectRequestListCnt = shareRequestService.selectRequestListCnt(memIdx);
        System.err.println("selectRequestListCnt: " + selectRequestListCnt);
        model.addAttribute("selectRequestList", selectRequestList);
        model.addAttribute("selectRequestListCnt", selectRequestListCnt);
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
        return "";
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
    public String confirmSharedGroupApprove(ShareRequestVO shareRequestVO, Model model) {
        ShareRequestVO request = shareRequestService.selectReceivedRequest(shareRequestVO.getReqIdx());
        model.addAttribute("request", request);
        return "popup/group/confirmSharedGroupApprove";
    }

    @PostMapping("/approveSharedGroup.do")
    public String approveSharedGroup(@ModelAttribute SharePermissionVO sharePermissionVO, ShareRequestVO shareRequestVO,Model model, HttpSession session) {
        String memIdx = (String) session.getAttribute("userIdx");
        String groIdx = (String) session.getAttribute("groIdx");
        shareRequestVO.setApprovedBy(memIdx);
        shareRequestVO.setStatus("APPROVED");
        shareRequestService.approvedRequest(shareRequestVO);

        sharePermissionVO.setOwnerMemIdx(memIdx);
        sharePermissionVO.setGroIdx(groIdx);
        sharePermissionVO.setTargetMemIdx(shareRequestVO.getReqMemIdx());

        sharePermissionService.insertPermission(sharePermissionVO);

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

    @RequestMapping("/writeRequest.do")
    public String writeRequest(Model model) {
        return "popup/group/writeRequest";
    }

    @PostMapping(value = "/checkInviteCode.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String checkInviteCode(@RequestParam(name = "inviteCode")String inviteCode , Model model) {
        boolean exists = shareInviteService.checkInviteCode(inviteCode);
        return exists ? "1" : "0";    }

    @PostMapping("/insertRequest.do")
    public String insertRequest(@RequestParam(name = "inviteCode")String inviteCode,ShareRequestVO shareRequestVO, HttpSession session ,Model model) {
        String memIdx = session.getAttribute("userIdx").toString();

        ShareInviteVO invite = shareInviteService.selectByInviteCode(inviteCode);
        shareRequestVO.setInvIdx(invite.getInvIdx());
        shareRequestVO.setReqMemIdx(memIdx);
        shareRequestVO.setStatus("PENDING");
        shareRequestService.insertShareRequest(shareRequestVO);

        return EgovframeworkCommonUtil.alertMoveWithScript(model, "공유 요청이 전송되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

}

