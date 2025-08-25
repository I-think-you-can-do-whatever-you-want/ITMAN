package egovframework.itman.share.sharePermission.service;

import java.util.List;

public interface SharePermissionService {
    List<SharePermissionVO> selectSharedGroupListByMemIdx(SharePermissionVO sharePermissionVO);
    int selectSharedGroupListByMemIdxCnt(SharePermissionVO sharePermissionVO);
}
