package egovframework.itman.location.service.impl;

import egovframework.itman.common.Pagination;
import egovframework.itman.location.service.LocationService;
import egovframework.itman.location.service.LocationVO;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("locationService")
public class LocationServiceImpl implements LocationService {
    @Resource
    LocationDAO locationDAO;

    @Override
    public List<LocationVO> selectLocationsByGroup(String groIdx) {
        return locationDAO.selectLocationsByGroup(groIdx);
    }

    @Override
    public List<LocationVO> selectLocationList(LocationVO vo) {
        return locationDAO.selectLocationList(vo);
    }

    @Override
    public int selectLocationListCnt(LocationVO vo) {
        return locationDAO.selectLocationListCnt(vo);
    }

    @Override
    public LocationVO selectLocation(LocationVO vo) throws Exception{
        return locationDAO.selectLocation(vo);
    }

    @Override
    public void insertAssetLocation(LocationVO vo) throws Exception {
         locationDAO.insertAssetLocation(vo);
    }

    @Override
    public void updateAssetLocation(LocationVO vo) throws Exception{
        locationDAO.updateAssetLocation(vo);
    }

    @Override
    public void deleteAssetLocation(LocationVO vo) throws Exception{
        locationDAO.deleteAssetLocation(vo);
    }

    @Override
    public LocationVO checkDuplicate(LocationVO vo){
        return locationDAO.checkDuplicate(vo);
    }

}
