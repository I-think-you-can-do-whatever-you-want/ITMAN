package egovframework.itman.share.shareHistory.service.impl;

import egovframework.itman.share.shareHistory.service.ShareHistoryService;
import egovframework.itman.share.shareHistory.service.ShareHistoryVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareHistoryServiceImpl implements ShareHistoryService {
    private final ShareHistoryDAO shareHistoryDAO;
    public ShareHistoryServiceImpl(ShareHistoryDAO shareHistoryDAO) {
        this.shareHistoryDAO = shareHistoryDAO;
    }

    @Override
    public List<ShareHistoryVO> selectMyShareHistory(String ownerMemIdx) {
        return shareHistoryDAO.selectMyShareHistory(ownerMemIdx);
    }

    @Override
    public List<ShareHistoryVO> selectSharedHistory(String targetMemIdx){
        return shareHistoryDAO.selectSharedHistory(targetMemIdx);
    }
}
