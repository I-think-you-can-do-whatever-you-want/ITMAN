package egovframework.itman.share.shareHistory.service;

import java.util.List;

public interface ShareHistoryService {
    List<ShareHistoryVO> selectMyShareHistory(String ownerMemIdx);
    List<ShareHistoryVO> selectSharedHistory(String targetMemIdx);
}
