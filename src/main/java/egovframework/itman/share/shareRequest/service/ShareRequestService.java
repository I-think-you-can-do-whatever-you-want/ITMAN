package egovframework.itman.share.shareRequest.service;

import java.util.List;

public interface ShareRequestService {
    List<ShareRequestVO> selectReceivedRequestList(String memIdx);
    int selectReceivedRequestListCnt(String memIdx);
}
