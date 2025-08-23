package egovframework.itman.division.web;

import egovframework.itman.division.service.DivisionService;
import egovframework.itman.division.service.DivisionVO;
import egovframework.itman.division.service.impl.DivisionServiceImpl;
import egovframework.usr.com.EgovframeworkCommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DivisionController {
    @Resource
    private DivisionService divisionService;

    @RequestMapping("/departList.do")
    public String selectDivisionList(DivisionVO divisionVO, Model model
            , @RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "1") int range
            , @RequestParam(value = "id",defaultValue = "3")int id
            , HttpSession session) throws Exception {
        model.addAttribute("pageNumDepth01", id);
        String groIdx = (String) session.getAttribute("groIdx");


        divisionVO.getPagination().setSearchingGroIdx(divisionVO.getPagination().getSearching(), groIdx);

        int listCnt = divisionService.selectDivisionListCnt(divisionVO);
        divisionVO.getPagination().pageInfo(page, range, listCnt);

        List<DivisionVO> list = divisionService.selectDivisionList(divisionVO);
        model.addAttribute("pagination", divisionVO.getPagination());
        model.addAttribute("resultList", list);

        return "inGroup/departList";
    }

    @RequestMapping("/divisionWrite.do")
    public String writeEmployeeDivision(DivisionVO vo, Model model) {
        if(vo.getDivIdx() != null){
            DivisionVO resultVO = divisionService.selectDivisionView(vo);
            model.addAttribute("division", resultVO);
        }
        return "popup/contWriteItmDivision";
    }
    @PostMapping(value = "/checkDuplicateEmpDiv.do" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String checkDuplicateEmpDiv(@RequestParam("divCode") String divCode,@RequestParam(name = "divIdx", required = false) String divIdx, HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        DivisionVO vo = new DivisionVO();
        vo.setDivCode(divCode);
        vo.setGroIdx(groIdx);
        vo.setDivIdx(divIdx);

        return divisionService.isDuplicateDivision(vo) ? "0" : "1";
    }

    @PostMapping("/insertDepart.do")
    public String insertDivision(DivisionVO vo, Model model, HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String regIdx = (String) session.getAttribute("userIdx");
        vo.setRegIdx(regIdx);
        divisionService.insertDivision(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "부서가 추가되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

    @PostMapping("/updateDepart.do")
    public String updateDivision(@ModelAttribute DivisionVO vo, Model model,HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String modIdx = (String) session.getAttribute("userIdx");
        vo.setModIdx(modIdx);
        divisionService.updateDivision(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "부서가 수정되었습니다","<script>window.opener.location.reload(); window.close();</script>");    }

    @RequestMapping("/confirmDivisionDel.do")
    public String confirmDivisionDel(DivisionVO vo, Model model) {
        model.addAttribute("division", vo);
        return "popup/contDivisionDel";
    }

    @PostMapping("/deleteDepart.do")
    public String deleteDivision(DivisionVO vo, Model model, HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String delIdx = (String) session.getAttribute("userIdx");
        vo.setDelIdx(delIdx);
        divisionService.deleteDivision(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "부서가 삭제되었습니다","<script>window.opener.location.reload(); window.close();</script>");    }



}
