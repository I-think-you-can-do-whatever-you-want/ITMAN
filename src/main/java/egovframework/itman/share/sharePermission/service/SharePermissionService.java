package egovframework.itman.share.sharePermission.service;

import java.util.List;

public interface SharePermissionService {
    List<SharePermissionVO> selectMySharedPermissionList(SharePermissionVO sharePermissionVO);
    int selectMySharedPermissionListCnt(SharePermissionVO sharePermissionVO);
    List<SharePermissionVO> selectSharedPermissionList(SharePermissionVO sharePermissionVO);
    int selectSharedPermissionListCnt(SharePermissionVO sharePermissionVO);
    void insertPermission(SharePermissionVO sharePermissionVO);
}
