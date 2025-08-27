package egovframework.itman.share.shareRequest.service;

import java.util.List;

public interface ShareRequestService {
    List<ShareRequestVO> selectReceivedRequestList(String memIdx);
    int selectReceivedRequestListCnt(String memIdx);
    List<ShareRequestVO> selectRequestList(String reqMemIdx);
    int selectRequestListCnt(String reqMemIdx);
    void insertShareRequest(ShareRequestVO shareRequestVO);
    ShareRequestVO selectReceivedRequest(String reqMemIdx);
    void approvedRequest(ShareRequestVO shareRequestVO);
}
