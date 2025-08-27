package egovframework.itman.share.shareRequest.service.impl;

import egovframework.itman.share.shareRequest.service.ShareRequestVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShareRequestDAO {
    @Autowired
    private SqlSession sqlSession;

    public List<ShareRequestVO> selectReceivedRequestList(String memIdx) {
        return sqlSession.selectList("shareRequestDAO.selectReceivedRequestList", memIdx);
    }

    public int selectReceivedRequestListCnt(String memIdx) {
        return sqlSession.selectOne("shareRequestDAO.selectReceivedRequestListCnt", memIdx);
    }

    public List<ShareRequestVO> selectRequestList(String reqMemIdx) {
        return sqlSession.selectList("shareRequestDAO.selectRequestList", reqMemIdx);
    }
    public int selectRequestListCnt(String reqMemIdx) {
        return sqlSession.selectOne("shareRequestDAO.selectRequestListCnt", reqMemIdx);
    }
    public void insertShareRequest(ShareRequestVO shareRequestVO){
        sqlSession.insert("shareRequestDAO.insertShareRequest", shareRequestVO);
    }
    public ShareRequestVO selectReceivedRequest(String reqMemIdx) {
        return sqlSession.selectOne("shareRequestDAO.selectReceivedRequest", reqMemIdx);
    }
    public void approvedRequest(ShareRequestVO shareRequestVO){
        sqlSession.update("shareRequestDAO.approvedRequest", shareRequestVO);
    }
}
