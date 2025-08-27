package egovframework.itman.share.shareInvite.service.impl;

import egovframework.itman.share.shareInvite.service.ShareInviteVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShareInviteDAO {
    @Autowired
    private SqlSession sqlSession;

    public int countByInviteCode(String inviteCode) {
        return sqlSession.selectOne("shareInviteDAO.countByInviteCode", inviteCode);
    }

    public List<ShareInviteVO> selectMyShareGroupList(ShareInviteVO shareInviteVO){
        return sqlSession.selectList("shareInviteDAO.selectMyShareGroupList", shareInviteVO);
    }

    public int selectMyShareGroupListCnt(ShareInviteVO shareInviteVO){
        return sqlSession.selectOne("shareInviteDAO.selectMyShareGroupListCnt", shareInviteVO);
    }

    public void insertShareInvite(ShareInviteVO shareInviteVO){
        sqlSession.insert("shareInviteDAO.insertShareInvite", shareInviteVO);
    }
    public boolean checkInviteCode(String inviteCode) {
        return sqlSession.selectOne("shareInviteDAO.checkInviteCode", inviteCode);
    }
    public ShareInviteVO selectByInviteCode(String inviteCode){
        return sqlSession.selectOne("shareInviteDAO.selectByInviteCode", inviteCode);
    }
}
