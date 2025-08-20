package egovframework.itman.position.service.impl;

import egovframework.itman.position.service.PositionVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PositionDAO {
    @Autowired
    private SqlSession sqlSession;

    public List<PositionVO> selectPositionsByGroup(String groIdx) {
        return sqlSession.selectList(
                "positionDAO.selectPositionsByGroup", groIdx);
    }
    public List<PositionVO> selectPositionList(PositionVO vo) {
        return sqlSession.selectList("positionDAO.selectPositionList", vo);
    }
    public int selectPositionListCnt(PositionVO vo) {
        return sqlSession.selectOne("positionDAO.selectPositionListCnt", vo);
    }

    public PositionVO selectPositionView(PositionVO positionVO) {
        return sqlSession.selectOne("positionDAO.selectPositionView", positionVO);
    }
    public void insertPosition(PositionVO positionVO) {
        sqlSession.insert("positionDAO.insertPosition", positionVO);
    }
    public void updatePosition(PositionVO positionVO) {
        sqlSession.update("positionDAO.updatePosition", positionVO);
    }
    public void deletePosition(PositionVO positionVO) {
        sqlSession.delete("positionDAO.deletePosition", positionVO);
    }
    public int checkDuplicate(PositionVO vo){
        return sqlSession.selectOne("positionDAO.checkDuplicate", vo);
    }
}
