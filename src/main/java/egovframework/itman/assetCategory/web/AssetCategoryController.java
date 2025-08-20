package egovframework.itman.assetCategory.web;

import egovframework.itman.assetCategory.service.AssetCategoryVO;
import egovframework.itman.assetCategory.service.impl.AssetCategoryServiceImpl;
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
public class AssetCategoryController {
    @Resource(name = "assetCategoryService")
    private AssetCategoryServiceImpl assetCategoryService;

    @RequestMapping("/assetCategory.do")
    public String selectAssetCategoryList(AssetCategoryVO assetCategoryVO, Model model,
                                          @RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "1") int range,
                                          @RequestParam(value = "id", defaultValue = "11")int id
    , HttpSession session) throws Exception {
        model.addAttribute("pageNumDepth01", id);
        String groIdx = (String) session.getAttribute("groIdx");

        assetCategoryVO.getPagination().setSearchingGroIdx(assetCategoryVO.getPagination().getSearching(), groIdx);

        int listCnt = assetCategoryService.selectAssetCategoryListCnt(assetCategoryVO);

        assetCategoryVO.getPagination().pageInfo(page, range, listCnt);
        assetCategoryVO.getPagination().setSearching(assetCategoryVO.getPagination().getSearching());

        List<AssetCategoryVO> resultList = assetCategoryService.selectAssetCategoryList(assetCategoryVO);
        model.addAttribute("resultList", resultList);
        model.addAttribute("pagination", assetCategoryVO.getPagination());

        return "inGroup/assetCategory";
    }

    @RequestMapping("/editAssetCategory.do")
    public String editAssetCategory(AssetCategoryVO vo, Model model) throws Exception {
        AssetCategoryVO resultVO = assetCategoryService.selectAssetCategory(vo);
        model.addAttribute("assetCategory", resultVO);
        return "popup/contEditAssetCategory";

    }

    @PostMapping("/updateAssetCategory.do")
    public String updateAssetCategory(AssetCategoryVO vo, Model model, HttpSession session) throws Exception {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String modIdx = (String) session.getAttribute("userIdx");
        vo.setModIdx(modIdx);
        assetCategoryService.updateAssetCategory(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "자산 분류가 수정되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

    @RequestMapping("/confirmAssetCategoryDel.do")
    public String confirmAssetCategoryDel(AssetCategoryVO vo, Model model) {
        model.addAttribute("assetCategory", vo);
        return "popup/listDelete";
    }

    @PostMapping("/deleteAssetCategory.do")
    public String deleteAssetCategory(AssetCategoryVO vo, Model model, HttpSession session) throws Exception {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String delIdx = (String) session.getAttribute("userIdx");
        vo.setDelIdx(delIdx);
        assetCategoryService.deleteAssetCategory(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "자산 분류가 삭제되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

}
