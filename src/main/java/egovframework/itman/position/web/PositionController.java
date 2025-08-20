package egovframework.itman.position.web;

import egovframework.itman.position.service.PositionVO;
import egovframework.itman.position.service.impl.PositionServiceImpl;
import egovframework.usr.com.EgovframeworkCommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.swing.text.Position;
import java.util.List;

@Controller
public class PositionController {
    @Resource(name = "positionService")
    public PositionServiceImpl positionService;

    @RequestMapping("/spotList.do")
    public String selectPositionList(PositionVO positionVO, Model model
    , @RequestParam(defaultValue = "1") int page
    , @RequestParam(defaultValue = "1") int range
    ,@RequestParam(value = "id",defaultValue = "4")int id
    , HttpSession session) {

        model.addAttribute("pageNumDepth01", id);
        String groIdx = (String) session.getAttribute("groIdx");

        positionVO.getPagination().setSearchingGroIdx(positionVO.getPagination().getSearching(), groIdx);
        int listCnt = positionService.selectPositionListCnt(positionVO);
        positionVO.getPagination().pageInfo(page, range, listCnt);
        List<PositionVO> list = positionService.selectPositionList(positionVO);
        model.addAttribute("pagination", positionVO.getPagination());
        model.addAttribute("resultList", list);

        return "itman/public/html/ingroup/spotList";

    }
    @RequestMapping("/positionWrite.do")
    public String writePosition(PositionVO vo, Model model) {
        if(vo.getPosIdx() !=null){
            PositionVO resultVO = positionService.selectPositionView(vo);
            model.addAttribute("position",resultVO);
        }
        return "itman/public/html/popup/contWriteItmPosition";
    }

    @RequestMapping("/positionEdit.do")
    public String editPosition(PositionVO vo, Model model){
            PositionVO resultVO = positionService.selectPositionView(vo);
            model.addAttribute("position",resultVO);
        return "itman/public/html/popup/contEditItmPosition";
    }

    @PostMapping("/insertPosition.do")
    public String insertPosition(PositionVO vo, Model model, HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String regIdx = (String) session.getAttribute("userIdx");
        vo.setRegIdx(regIdx);
        positionService.insertPosition(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "직위가 추가되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

    @PostMapping("/updatePosition.do")
    public String updatePosition(PositionVO vo, Model model){
        positionService.updatePosition(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "직위가 수정되었습니다","<script>window.opener.location.reload(); window.close();</script>");

    }

    @RequestMapping("/confirmPositionDel.do")
    public String confirmPositionDel(PositionVO vo, Model model){
        PositionVO resultVO = positionService.selectPositionView(vo);
        model.addAttribute("position",resultVO);
        return "itman/public/html/popup/listDelete";
    }

    @PostMapping("/deletePosition.do")
    public String deletePosition(PositionVO vo, Model model) {
        positionService.deletePosition(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "직위가 삭제되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

}
