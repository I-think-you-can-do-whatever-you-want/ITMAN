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

    public List<SharePermissionVO> selectMySharedPermissionList(SharePermissionVO sharePermissionVO){
        return sqlSession.selectList("sharePermissionDAO.selectMySharedPermissionList", sharePermissionVO);
    }
    public int selectMySharedPermissionListCnt(SharePermissionVO sharePermissionVO){
        return sqlSession.selectOne("sharePermissionDAO.selectMySharedPermissionListCnt", sharePermissionVO);
    }
    public List<SharePermissionVO> selectSharedPermissionList(SharePermissionVO sharePermissionVO) {
        return sqlSession.selectList("sharePermissionDAO.selectSharedPermissionList", sharePermissionVO);
    }
    public int selectSharedPermissionListCnt(SharePermissionVO sharePermissionVO) {
        return sqlSession.selectOne("sharePermissionDAO.selectSharedPermissionListCnt", sharePermissionVO);
    }

    public void insertPermission(SharePermissionVO sharePermissionVO){
        sqlSession.insert("sharePermissionDAO.insertPermission", sharePermissionVO);
    }
}
