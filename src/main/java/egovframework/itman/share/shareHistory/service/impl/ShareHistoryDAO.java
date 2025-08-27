package egovframework.itman.share.shareHistory.service.impl;

import egovframework.itman.share.shareHistory.service.ShareHistoryVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShareHistoryDAO {
    @Autowired
    private SqlSession sqlSession;

    public List<ShareHistoryVO> selectMyShareHistory(String ownerMemIdx) {
        return sqlSession.selectList("shareHistoryDAO.selectMyShareHistory");
    }

    public List<ShareHistoryVO> selectSharedHistory(String targetMemIdx){
        return sqlSession.selectList("shareHistoryDAO.selectSharedHistory");
    }
}
