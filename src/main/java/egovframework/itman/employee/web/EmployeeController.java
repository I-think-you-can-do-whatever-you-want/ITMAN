package egovframework.itman.employee.web;


import egovframework.itman.asset.service.AssetVO;
import egovframework.itman.asset.service.impl.AssetServiceImpl;
import egovframework.itman.division.service.DivisionVO;
import egovframework.itman.division.service.impl.DivisionServiceImpl;
import egovframework.itman.empState.service.EmpStateVO;
import egovframework.itman.employee.service.EmployeeVO;
import egovframework.itman.employee.service.impl.EmployeeServiceImpl;
import egovframework.itman.position.service.PositionVO;
import egovframework.itman.position.service.impl.PositionServiceImpl;
import egovframework.itman.empState.service.impl.EmpStateServiceImpl;
import egovframework.usr.com.EgovframeworkCommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

public class EmployeeController {
    @Resource(name = "employeeService")
    private EmployeeServiceImpl employeeService;
    @Resource(name = "divisionService")
    private DivisionServiceImpl divisionService;
    @Resource(name = "empStateService")
    private EmpStateServiceImpl empStateService;
    @Resource(name = "positionService")
    private PositionServiceImpl positionService;
    @Resource(name = "assetService")
    private AssetServiceImpl assetService;

    private static final String BASE_LOC = "itman/public/html/ingroup/";

    private void addCommonLists(String groIdx, Model model) {
        model.addAttribute("divisionList", divisionService.selectDivisionsByGroup(groIdx));
        model.addAttribute("empStateList", empStateService.selectEmpStatesByGroup(groIdx));
        model.addAttribute("positionList", positionService.selectPositionsByGroup(groIdx));
    }
    // --------------------조회--------------------
    @RequestMapping("/employeeList.do")
    public String selectEmployeeList(EmployeeVO employeeVO, Model model,
                                     @RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "1") int range,
                                     HttpSession session,
                                     @RequestParam(value = "id", defaultValue = "2") int id) throws Exception {
        model.addAttribute("pageNumDepth01", id);
        String groIdx = (String) session.getAttribute("groIdx");

        employeeVO.getPagination().setSearchingGroIdx(employeeVO.getPagination().getSearching(), groIdx);


        int listCnt = employeeService.selectEmployeeListCnt(employeeVO);
        employeeVO.getPagination().pageInfo(page, range, listCnt);
        employeeVO.getPagination().setSearching(employeeVO.getPagination().getSearching());

        employeeVO.setPagination(employeeVO.getPagination());

        // 공통 데이터
        addCommonLists(groIdx, model);

        List<EmployeeVO> list = employeeService.selectEmployeeList(employeeVO);

        model.addAttribute("pagination", employeeVO.getPagination());
        model.addAttribute("resultList", list);
        return BASE_LOC + "emploList";
    }


    @RequestMapping("/employeeView.do")
    public String selectEmployeeView(EmployeeVO vo, Model model) throws Exception {
        EmployeeVO resultVO = employeeService.selectEmployeeView(vo);
        List<AssetVO> assetList = assetService.selectEmpAssetList(resultVO);
        model.addAttribute("employee", resultVO);
        model.addAttribute("assetList", assetList);
        return BASE_LOC + "emploView";
    }

    // ---------------------생성--------------------------

    @RequestMapping("/employeeWrite.do")
    public String employeeForm(EmployeeVO vo, Model model, HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        addCommonLists(groIdx, model);
        return BASE_LOC + "emploWrite";
    }

    @RequestMapping("/emploDivisionWrite.do")
    public String writeEmployeeDivision(DivisionVO vo, Model model) {
        model.addAttribute("division", vo);
        return "itman/public/html/popup/employee/emploDivisionWrite";
    }

    @PostMapping("/insertEmploDivision.do")
    public String insertEmployeeDivision(DivisionVO vo, Model model, HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String regIdx = (String) session.getAttribute("userIdx");
        vo.setRegIdx(regIdx);
        divisionService.insertDivision(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "부서가 추가되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

    @RequestMapping("/emploPositionWrite.do")
    public String writeEmployeePosition(PositionVO vo, Model model) {
        model.addAttribute("position", vo);
        return "itman/public/html/popup/employee/emploPositionWrite";
    }

    @PostMapping(value = "/checkDuplicateEmpPos.do" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String checkDuplicateEmpPos(@RequestParam("posCode") String posCode,@RequestParam(name = "posIdx", required = false) String posIdx, HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        PositionVO vo = new PositionVO();
        vo.setPosCode(posCode);
        vo.setGroIdx(groIdx);
        vo.setPosIdx(posIdx);

        return positionService.isDuplicatePosition(vo) ? "0" : "1";
    }
    @PostMapping("/insertEmploPosition.do")
    public String insertEmployeePosition(PositionVO vo, Model model, HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String regIdx = (String) session.getAttribute("userIdx");
        vo.setRegIdx(regIdx);
        positionService.insertPosition(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "직위가 추가되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

    @RequestMapping("/emploStateWrite.do")
    public String writeEmployeeState(EmpStateVO vo, Model model) {
        model.addAttribute("empState", vo);
        return "itman/public/html/popup/employee/emploStateWrite";
    }

    @PostMapping(value = "/checkDuplicateEmpSta.do" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String checkDuplicateEmpSta(@RequestParam("empStCode") String empStCode, @RequestParam(name = "empStIdx", required = false) String empStIdx, HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        EmpStateVO vo = new EmpStateVO();
        vo.setEmpStCode(empStCode);
        vo.setGroIdx(groIdx);
        vo.setEmpStIdx(empStIdx);

        return empStateService.isDuplicateEmpState(vo) ? "0" : "1";
    }
    @PostMapping("/insertEmploState.do")
    public String insertEmployeeState(EmpStateVO vo, Model model, HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String regIdx = (String) session.getAttribute("userIdx");
        vo.setRegIdx(regIdx);
        empStateService.insertEmployeeState(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "직원 상태가 추가되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

    // --------------------수정----------------------------

    @RequestMapping("/emploTellInfoEdit.do")
    public String employeeTelEdit(EmployeeVO vo, Model model) {
        EmployeeVO resultVO = employeeService.selectEmployeeView(vo);
        model.addAttribute("employee", resultVO);

        return "itman/public/html/popup/employee/emploTelInfoEdit";
    }

    @RequestMapping("/updateEmploTelInfo.do")
    public String updateEmploTelInfo(EmployeeVO vo, Model model, HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String modIdx = (String) session.getAttribute("userIdx");
        vo.setModIdx(modIdx);
        employeeService.updateEmploTelInfo(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "직원 연락처가 수정되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

    @RequestMapping("/emploDivisionInfoEdit.do")
    public String employeeDivisionEdit(EmployeeVO vo, Model model) {
            EmployeeVO resultVO = employeeService.selectEmployeeView(vo);
            model.addAttribute("employee", resultVO);
            String groIdx = resultVO.getGroIdx();
        model.addAttribute("divisionList", divisionService.selectDivisionsByGroup(groIdx));

        return "itman/public/html/popup/employee/emploDivisionInfoEdit";
    }
    @RequestMapping("/updateEmploDivisionInfo.do")
    public String updateEmploDivisionInfo(EmployeeVO vo, Model model, HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String modIdx = (String) session.getAttribute("userIdx");
        vo.setModIdx(modIdx);
        employeeService.updateEmploDivisionInfo(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "직원 부서가 수정되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

    @RequestMapping("/emploPosInfoEdit.do")
    public String employeePosEdit(EmployeeVO vo, Model model) {
        EmployeeVO resultVO = employeeService.selectEmployeeView(vo);
        model.addAttribute("employee", resultVO);
        String groIdx = resultVO.getGroIdx();

        model.addAttribute("positionList", positionService.selectPositionsByGroup(groIdx));


        return "itman/public/html/popup/employee/emploPosInfoEdit";
    }
    @RequestMapping("/updateEmploPosInfo.do")
    public String updateEmploPosInfo(EmployeeVO vo, Model model, HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String modIdx = (String) session.getAttribute("userIdx");
        vo.setModIdx(modIdx);
        employeeService.updateEmploPosInfo(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "직원 직위가 수정되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

    @RequestMapping("/emploMailInfoEdit.do")
    public String employeeMailEdit(EmployeeVO vo, Model model) {
        EmployeeVO resultVO = employeeService.selectEmployeeView(vo);
        model.addAttribute("employee", resultVO);

        return "itman/public/html/popup/employee/emploMailInfoEdit";
    }

    @RequestMapping("/updateEmploMailInfo.do")
    public String updateEmploMailInfo(EmployeeVO vo, Model model , HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String modIdx = (String) session.getAttribute("userIdx");
        vo.setModIdx(modIdx);

        employeeService.updateEmploMailInfo(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "직원 메일이 수정되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

    @RequestMapping("/emploNameInfoEdit.do")
    public String employeeNameEdit(EmployeeVO vo, Model model) {
        EmployeeVO resultVO = employeeService.selectEmployeeView(vo);
        model.addAttribute("employee", resultVO);

        return "itman/public/html/popup/employee/emploNameInfoEdit";
    }

    @RequestMapping("/updateEmploNameInfo.do")
    public String updateEmploNameInfo(EmployeeVO vo, Model model ) {
        employeeService.updateEmploNameInfo(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "직원명이 수정되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

    @RequestMapping("/emploStateInfoEdit.do")
    public String employeeStateEdit(EmployeeVO vo, Model model) {
            EmployeeVO resultVO = employeeService.selectEmployeeView(vo);
            model.addAttribute("employee", resultVO);
            String groIdx = resultVO.getGroIdx();

        model.addAttribute("empStateList", empStateService.selectEmpStatesByGroup(groIdx));

        return "itman/public/html/popup/employee/emploStateInfoEdit";
    }

    @RequestMapping("/updateEmploStateInfo.do")
    public String updateEmploStateInfo(EmployeeVO vo, Model model , HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String modIdx = (String) session.getAttribute("userIdx");
        vo.setModIdx(modIdx);
        employeeService.updateEmploStateInfo(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "직원 상태가 수정되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

    @RequestMapping("/emploNumInfoEdit.do")
    public String employeeNumEdit(EmployeeVO vo, Model model) {
            EmployeeVO resultVO = employeeService.selectEmployeeView(vo);
            model.addAttribute("employee", resultVO);

        return "itman/public/html/popup/employee/emploNumInfoEdit";
    }

    @RequestMapping("/updateEmploNumInfo.do")
    public String updateEmploNumInfo(EmployeeVO vo, Model model , HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String modIdx = (String) session.getAttribute("userIdx");
        vo.setModIdx(modIdx);
        employeeService.updateEmploNumInfo(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "직원 사번이 수정되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

    @RequestMapping("/insert.do")
    public String insertEmployee(EmployeeVO vo, Model model, HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String regIdx = (String) session.getAttribute("userIdx");
        vo.setRegIdx(regIdx);
        employeeService.insertEmployee(vo);
        return EgovframeworkCommonUtil.alertMove(model, "직원이 추가되었습니다", "/employeeList.do");
    }

    @RequestMapping("/emploDelConfirm.do")
    public String deleteEmploConfirm(EmployeeVO vo, Model model) {
        EmployeeVO resultVO = employeeService.selectEmployeeView(vo);
        model.addAttribute("employee", resultVO);
        return "itman/public/html/popup/employee/emploDel";
    }

    @RequestMapping("/emploDel.do")
    public String deleteEmployee(EmployeeVO vo, Model model, HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String delIdx = (String) session.getAttribute("userIdx");
        vo.setDelIdx(delIdx);
        employeeService.deleteEmployee(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "직원이 삭제되었습니다","<script>window.opener.location='/employeeList.do'; window.close();</script>");
    }

    @RequestMapping("/popup/searchPop.do")
    public String searchPop(EmployeeVO employeeVO, Model model
            , @RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "1") int range
            , HttpSession session) throws Exception {
        String groIdx = (String) session.getAttribute("groIdx");

        employeeVO.getPagination().setSearchingGroIdx(employeeVO.getPagination().getSearching(), groIdx);

        int listCnt = employeeService.selectEmployeeListCnt(employeeVO);
        employeeVO.getPagination().pageInfo(page, range, listCnt);
        employeeVO.getPagination().setSearching(employeeVO.getPagination().getSearching());
        //검색 결과에 따른 총 목록의 길이를 반환
        List<EmployeeVO> list = employeeService.selectEmployeeList(employeeVO);
        //페이징 구현
        model.addAttribute("pagination", employeeVO.getPagination());
        model.addAttribute("employeeList", list);
        return "itman/public/html/popup/searchPop";
    }


}
