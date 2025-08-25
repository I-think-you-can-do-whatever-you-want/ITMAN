package egovframework.itman.share.shareRequest.service.impl;

import egovframework.itman.share.shareRequest.service.ShareRequestService;
import egovframework.itman.share.shareRequest.service.ShareRequestVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareRequestServiceImpl implements ShareRequestService {
    private final ShareRequestDAO shareRequestDAO;

    public ShareRequestServiceImpl(ShareRequestDAO shareRequestDAO) {
        this.shareRequestDAO = shareRequestDAO;
    }

    @Override
    public List<ShareRequestVO> selectReceivedRequestList(String memIdx) {
        return shareRequestDAO.selectReceivedRequestList(memIdx);
    }

    @Override
    public int selectReceivedRequestListCnt(String memIdx) {
        return shareRequestDAO.selectReceivedRequestListCnt(memIdx);
    }
}
