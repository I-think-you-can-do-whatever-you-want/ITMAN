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
    public List<SharePermissionVO> selectSharedGroupListByMemIdx(SharePermissionVO sharePermissionVO){
        return sharePermissionDAO.selectSharedGroupListByMemIdx(sharePermissionVO);
    }
    @Override
    public int selectSharedGroupListByMemIdxCnt(SharePermissionVO sharePermissionVO){
        return sharePermissionDAO.selectSharedGroupListByMemIdxCnt(sharePermissionVO);
    }
}
