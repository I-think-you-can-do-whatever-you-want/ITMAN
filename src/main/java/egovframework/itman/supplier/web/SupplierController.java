package egovframework.itman.supplier.web;

import egovframework.itman.supplier.service.SupplierService;
import egovframework.itman.supplier.service.SupplierVO;
import egovframework.itman.supplier.service.impl.SupplierServiceImpl;
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
public class SupplierController {
    @Resource
    SupplierService supplierService;

    @RequestMapping("/supplierList.do")
    public String selectSupplierList(SupplierVO supplierVO, Model model
            , @RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "1") int range
            , @RequestParam(value = "id", defaultValue = "5")int id
            , HttpSession session) throws Exception {
        model.addAttribute("pageNumDepth01", id);
        String groIdx = (String) session.getAttribute("groIdx");

        supplierVO.getPagination().setSearchingGroIdx(supplierVO.getPagination().getSearching(), groIdx);
        int listCnt = supplierService.selectSupplierListCnt(supplierVO);
        supplierVO.getPagination().pageInfo(page, range, listCnt);

        List<SupplierVO> list = supplierService.selectSupplierList(supplierVO);
        model.addAttribute("pagination", supplierVO.getPagination());
        model.addAttribute("resultList", list);
        return "inGroup/buyList";
    }

    @RequestMapping("/popup/selectAssetSupplier.do")
    public String supplierPop(SupplierVO supplierVO, Model model
            , @RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "1") int range
            , HttpSession session) throws Exception {
        String groIdx = (String) session.getAttribute("groIdx");

        supplierVO.getPagination().setSearchingGroIdx(supplierVO.getPagination().getSearching(), groIdx);
        int listCnt = supplierService.selectSupplierListCnt(supplierVO);
        supplierVO.getPagination().pageInfo(page, range, listCnt);
        supplierVO.getPagination().setSearching(supplierVO.getPagination().getSearching());

        List<SupplierVO> list = supplierService.selectSupplierList(supplierVO);

        model.addAttribute("pagination", supplierVO.getPagination());
        model.addAttribute("supplierList", list);
        return "popup/supplierPop";
    }

    @RequestMapping("/supplierEdit.do")
    public String supplierEdit(SupplierVO vo, Model model) throws Exception {
        SupplierVO supply = supplierService.selectSupplyView(vo);
        model.addAttribute("supply", supply);
        return "popup/contEditItmSupplier";
    }

    @PostMapping("/updateSupplier.do")
    public String updateSupply(SupplierVO vo, Model model, HttpSession session) throws Exception {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String modIdx = (String) session.getAttribute("userIdx");
        vo.setModIdx(modIdx);
        supplierService.updateSupply(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "구매처가 수정되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

    @RequestMapping("/confirmSupplierDel.do")
    public String confirmSupplierDel(SupplierVO vo, Model model) {
        model.addAttribute("supply", vo);
        return "popup/listDelete";
    }

    @PostMapping("/deleteSupplier.do")
    public String deleteSupply(SupplierVO vo, Model model, HttpSession session) throws Exception {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String delIdx = (String) session.getAttribute("userIdx");
        vo.setDelIdx(delIdx);
        supplierService.deleteSupply(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "구매처가 삭제되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }
}
