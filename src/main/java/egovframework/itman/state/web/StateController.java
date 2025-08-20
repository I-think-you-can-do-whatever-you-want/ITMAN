package egovframework.itman.state.web;

import egovframework.itman.state.service.StateVO;
import egovframework.itman.state.service.impl.StateServiceImpl;
import egovframework.usr.com.EgovframeworkCommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StateController {
    @Resource(name = "stateService")
    private StateServiceImpl stateService;

    @RequestMapping("/assetStateList.do")
    public String selectAssetStateList(StateVO stateVO, Model model
    , @RequestParam(defaultValue = "1") int page
    , @RequestParam(defaultValue = "1") int range
    , @RequestParam(value = "id", defaultValue = "7")int id
    , HttpSession session) throws Exception {
        model.addAttribute("pageNumDepth01", id);
        String groIdx = (String) session.getAttribute("groIdx");

        stateVO.getPagination().setSearchingGroIdx(stateVO.getPagination().getSearching(), groIdx);

        int listCnt = stateService.selectAssetStateListCnt(stateVO);
        stateVO.getPagination().pageInfo(page, range, listCnt);
        stateVO.getPagination().setSearching(stateVO.getPagination().getSearching());

        List<StateVO> list = stateService.selectAssetStateList(stateVO);
        model.addAttribute("pagination", stateVO.getPagination());
        model.addAttribute("resultList", list);

        return "inGroup/aStatList";
    }

    @RequestMapping("/editAssetState.do")
    public String editAssetState(StateVO vo, Model model) throws Exception {
        StateVO resultVO = stateService.selectAssetStateView(vo);
        model.addAttribute("state", resultVO);
        return "popup/contWrite";
    }

    @PostMapping("/updateState.do")
    public String updateAssetState(StateVO vo, Model model, HttpSession session) throws Exception {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String modIdx = (String) session.getAttribute("userIdx");
        vo.setModIdx(modIdx);
        stateService.updateAssetState(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "자산 상태가 수정되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

    @RequestMapping("/confirmAssetStateDel.do")
    public String confirmAssetStateDel(StateVO vo, Model model) {
        model.addAttribute("state", vo);
        return "popup/contDel";
    }

    @RequestMapping("/deleteAssetState.do")
    public String deleteAssetState(StateVO vo, Model model, HttpSession session) throws Exception {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String delIdx = (String) session.getAttribute("userIdx");
        vo.setDelIdx(delIdx);
        stateService.deleteAssetState(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "자산 상태가 삭제되었습니다", "<script>window.opener.location.reload(); window.close();</script>");
    }
}
