package egovframework.itman.assLog.service.impl;

import egovframework.itman.assLog.service.AssLogVO;
import egovframework.itman.common.Pagination;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssLogDAO {
    @Autowired
    SqlSession sqlSession;

    public void insertAssLog(AssLogVO assLogVO){
        sqlSession.insert("assLogDAO.insertAssLog", assLogVO);
    }

    public List<AssLogVO> selectAssLogList(String assIdx){
        return sqlSession.selectList("assLogDAO.selectAssLogList", assIdx);
    }

    public List<AssLogVO> selectDashBoardAssLogList(String groIdx) {
        return sqlSession.selectList("assLogDAO.selectDashBoardAssLogList", groIdx);
    }
    public List<AssLogVO> selectAllAssLogList(AssLogVO vo){
        return sqlSession.selectList("assLogDAO.selectAllAssLogList", vo);
    }

    public int selectAssLogListCnt(AssLogVO vo){
        return sqlSession.selectOne("assLogDAO.selectAssLogListCnt", vo);
    }

}
