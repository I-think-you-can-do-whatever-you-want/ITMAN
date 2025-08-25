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
        return sqlSession.selectList("selectReceivedRequestList", memIdx);
    }

    public int selectReceivedRequestListCnt(String memIdx) {
        return sqlSession.selectOne("selectReceivedRequestListCnt", memIdx);
    }
}
