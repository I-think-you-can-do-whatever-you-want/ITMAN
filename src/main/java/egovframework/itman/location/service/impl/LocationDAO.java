package egovframework.itman.location.service.impl;

import egovframework.itman.common.Pagination;
import egovframework.itman.location.service.LocationVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationDAO {
    @Autowired
    SqlSession sqlSession;

    public List<LocationVO> selectLocationsByGroup(String groIdx) {
        return sqlSession.selectList("locationDAO.selectLocationsByGroup", groIdx);
    }
    public List<LocationVO> selectLocationList(LocationVO vo) {
        return sqlSession.selectList("locationDAO.selectLocationList", vo);
    }
    public int selectLocationListCnt(LocationVO vo) {
        return sqlSession.selectOne("locationDAO.selectLocationListCnt", vo);
    }

    public LocationVO selectLocation(LocationVO vo) throws Exception{
        return sqlSession.selectOne("locationDAO.selectLocation", vo);
    }

    public void insertAssetLocation(LocationVO vo) throws Exception {
        sqlSession.insert("locationDAO.insertAssetLocation", vo);
    }

    public void updateAssetLocation(LocationVO vo) throws Exception{
        sqlSession.update("locationDAO.updateAssetLocation", vo);
    }

    public void deleteAssetLocation(LocationVO vo) throws Exception{
        sqlSession.update("locationDAO.deleteAssetLocation", vo);
    }

    public LocationVO checkDuplicate(LocationVO vo){
        return sqlSession.selectOne("locationDAO.checkDuplicate", vo);
    }


}
