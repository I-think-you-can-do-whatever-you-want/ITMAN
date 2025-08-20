package egovframework.itman.location.web;

import egovframework.itman.location.service.LocationVO;
import egovframework.itman.location.service.impl.LocationServiceImpl;
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
public class LocationController {
    @Resource(name = "locationService")
    LocationServiceImpl locationService;

    @RequestMapping("/popup/locationPop.do")
    public String locationPop(LocationVO locationVO, Model model
            , @RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "1") int range
            , HttpSession session) {
        String groIdx = (String) session.getAttribute("groIdx");

        locationVO.getPagination().setSearchingGroIdx(locationVO.getPagination().getSearching(), groIdx);

        int listCnt = locationService.selectLocationListCnt(locationVO);
        locationVO.getPagination().pageInfo(page, range, listCnt);
        locationVO.getPagination().setSearching(locationVO.getPagination().getSearching());

        List<LocationVO> resultList = locationService.selectLocationList(locationVO);
        model.addAttribute("pagination", locationVO.getPagination());
        model.addAttribute("locations", resultList);
        return "popup/LocationPop";
    }

    @RequestMapping("/assetLocationList.do")
    public String locationList(LocationVO locationVO, Model model
    , @RequestParam(defaultValue = "1") int page
    , @RequestParam(defaultValue = "1") int range
    , @RequestParam(value = "id", defaultValue = "6")int id
    , HttpSession session) {
        model.addAttribute("pageNumDepth01", id);
        String groIdx = (String) session.getAttribute("groIdx");

        locationVO.getPagination().setSearchingGroIdx(locationVO.getPagination().getSearching(), groIdx);

    int listCnt = locationService.selectLocationListCnt(locationVO);
        locationVO.getPagination().pageInfo(page, range, listCnt);
        locationVO.getPagination().setSearching(locationVO.getPagination().getSearching());

    List<LocationVO> list = locationService.selectLocationList(locationVO);
    model.addAttribute("pagination", locationVO.getPagination());
    model.addAttribute("resultList", list);

        return "inGroup/locList";
    }

    @RequestMapping("/editLocation.do")
    public String editLocation(LocationVO vo, Model model) throws Exception {
        LocationVO resultVO = locationService.selectLocation(vo);
        model.addAttribute("location", resultVO);
        return "popup/contEditItmLocation";
    }

    @PostMapping("/updateLocation.do")
    public String updateLocation(LocationVO vo, Model model, HttpSession session) throws Exception {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String modIdx = (String) session.getAttribute("userIdx");
        vo.setModIdx(modIdx);
        locationService.updateAssetLocation(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "자산 위치가 수정되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

    @RequestMapping("/confirmLocationDel.do")
    public String confirmLocationDel(LocationVO vo, Model model) {
        model.addAttribute("location", vo);
        return "popup/listDelete";
    }


    @PostMapping("/deleteLocation.do")
    public String deleteLocation(LocationVO vo, Model model, HttpSession session) throws Exception {
        String groIdx = (String) session.getAttribute("groIdx");
        vo.setGroIdx(groIdx);
        String delIdx = (String) session.getAttribute("userIdx");
        vo.setDelIdx(delIdx);
        locationService.deleteAssetLocation(vo);
        return EgovframeworkCommonUtil.alertMoveWithScript(model, "자산 위치가 삭제되었습니다","<script>window.opener.location.reload(); window.close();</script>");
    }

}
