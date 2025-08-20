package egovframework.itman.state.service.impl;

import egovframework.itman.common.Pagination;
import egovframework.itman.state.service.StateVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StateDAO {
    @Autowired
    private SqlSession sqlSession;

    public List<StateVO> selectAssetStateList(StateVO vo) throws Exception {
        return sqlSession.selectList("stateDAO.selectAssetStateList", vo);
    }
    public int selectAssetStateListCnt(StateVO vo) throws Exception {
        return sqlSession.selectOne("stateDAO.selectAssetStateListCnt", vo);
    }
    public List<StateVO> selectDashBoardAssetStateList(String groIdx) throws Exception{
        return sqlSession.selectList("stateDAO.selectDashBoardAssetStateList", groIdx);
    }

    public StateVO selectAssetStateView(StateVO vo) throws Exception{
        return sqlSession.selectOne("stateDAO.selectAssetStateView", vo);
    }

    public List<StateVO> selectStatesByGroup(String groIdx) throws Exception {
        return sqlSession.selectList("stateDAO.selectStatesByGroup", groIdx);
    }

    public void insertAssetState(StateVO vo) throws Exception {
        sqlSession.insert("stateDAO.insertAssetState", vo);
    }
    public void updateAssetState(StateVO vo) throws Exception {
        sqlSession.update("stateDAO.updateAssetState", vo);
    }
    public void deleteAssetState(StateVO vo) throws Exception {
        sqlSession.update("stateDAO.deleteAssetState", vo);
    }
    public int checkDuplicate(StateVO vo){
        return sqlSession.selectOne("stateDAO.checkDuplicate", vo);
    }
}
