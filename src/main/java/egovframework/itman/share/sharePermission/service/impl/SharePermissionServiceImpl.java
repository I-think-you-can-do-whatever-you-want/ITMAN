package egovframework.itman.share.sharePermission.service.impl;

import egovframework.itman.share.sharePermission.service.SharePermissionService;
import egovframework.itman.share.sharePermission.service.SharePermissionVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SharePermissionServiceImpl implements SharePermissionService {
    private final SharePermissionDAO sharePermissionDAO;
    public SharePermissionServiceImpl(SharePermissionDAO sharePermissionDAO) {
        this.sharePermissionDAO = sharePermissionDAO;
    }

    @Override
    public List<SharePermissionVO> selectMySharedPermissionList(SharePermissionVO sharePermissionVO){
        return sharePermissionDAO.selectMySharedPermissionList(sharePermissionVO);
    }
    @Override
    public int selectMySharedPermissionListCnt(SharePermissionVO sharePermissionVO){
        return sharePermissionDAO.selectMySharedPermissionListCnt(sharePermissionVO);
    }

    @Override
    public List<SharePermissionVO> selectSharedPermissionList(SharePermissionVO sharePermissionVO) {
        return sharePermissionDAO.selectSharedPermissionList(sharePermissionVO);
    }

    @Override
    public int selectSharedPermissionListCnt(SharePermissionVO sharePermissionVO) {
        return sharePermissionDAO.selectSharedPermissionListCnt(sharePermissionVO);
    }

    @Override
    public void insertPermission(SharePermissionVO sharePermissionVO){
        sharePermissionDAO.insertPermission(sharePermissionVO);
    }
}
