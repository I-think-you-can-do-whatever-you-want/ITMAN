package egovframework.itman.share.sharePermission.service.impl;

import egovframework.itman.share.sharePermission.service.SharePermissionVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SharePermissionDAO {
    @Autowired
    private SqlSession sqlSession;

    public List<SharePermissionVO> selectSharedGroupListByMemIdx(SharePermissionVO sharePermissionVO){
        return sqlSession.selectList("sharePermissionDAO.selectSharedGroupListByMemIdx", sharePermissionVO);
    }
    public int selectSharedGroupListByMemIdxCnt(SharePermissionVO sharePermissionVO){
        return sqlSession.selectOne("sharePermissionDAO.selectSharedGroupListByMemIdxCnt", sharePermissionVO);
    }
}
