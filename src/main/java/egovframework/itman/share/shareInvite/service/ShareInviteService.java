package egovframework.itman.share.shareInvite.service;

import java.util.List;

public interface ShareInviteService {
    String generateUniqueInviteCode(int length) throws Exception;
    List<ShareInviteVO> selectMyShareGroupList(ShareInviteVO shareInviteVO);
    int selectMyShareGroupListCnt(ShareInviteVO shareInviteVO);
    void insertShareInvite(ShareInviteVO shareInviteVO);

}
