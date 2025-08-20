package egovframework.itman.assLog.web;

import egovframework.itman.assLog.service.AssLogVO;
import egovframework.itman.assLog.service.impl.AssLogServiceImpl;
import egovframework.itman.common.Pagination;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AssLogController {
    @Resource(name = "assLogService")
    private AssLogServiceImpl assLogService;

    @RequestMapping("/assetHistory.do")
    public String selectAssetHistoryList(AssLogVO assLogVO, Model model
    , @RequestParam(defaultValue = "1") int page
    , @RequestParam(defaultValue = "1") int range
    , @RequestParam(value = "id", defaultValue = "9")int id ,
                                         HttpSession session) {
        model.addAttribute("pageNumDepth01", id);
        String groIdx = (String) session.getAttribute("groIdx");

        assLogVO.getPagination().setSearchingGroIdx(assLogVO.getPagination().getSearching(),groIdx);
        assLogVO.getPagination().setListCnt(assLogService.selectAssLogListCnt(assLogVO));

        assLogVO.getPagination().pageInfo(page,range,assLogVO.getPagination().getListCnt());
        List<AssLogVO> list = assLogService.selectAllAssLogList(assLogVO);
        System.out.println("ORDER BY = " + assLogVO.getPagination().getSearching().getOrderBy());

        model.addAttribute("pagination", assLogVO.getPagination());
        model.addAttribute("resultList", list);
        return "itman/public/html/ingroup/ahistory";
    }
}
