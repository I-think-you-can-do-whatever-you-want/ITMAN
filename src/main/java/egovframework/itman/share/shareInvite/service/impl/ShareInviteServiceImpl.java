package egovframework.itman.share.shareInvite.service.impl;

import egovframework.itman.common.CodeGenerator;
import egovframework.itman.share.shareInvite.service.ShareInviteService;
import egovframework.itman.share.shareInvite.service.ShareInviteVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareInviteServiceImpl implements ShareInviteService {
    private final ShareInviteDAO shareInviteDAO;


    public ShareInviteServiceImpl(ShareInviteDAO shareInviteDAO) {
        this.shareInviteDAO = shareInviteDAO;
    }

    @Override
    public String generateUniqueInviteCode(int length) throws Exception {
        String code;
        do {
            code = CodeGenerator.generateCode(length);
        } while (shareInviteDAO.countByInviteCode(code) > 0); // DB에 존재하면 다시 생성
        return code;
    }

    @Override
    public List<ShareInviteVO> selectMyShareGroupList(ShareInviteVO shareInviteVO){
        return shareInviteDAO.selectMyShareGroupList(shareInviteVO);
    }

    @Override
    public int selectMyShareGroupListCnt(ShareInviteVO shareInviteVO){
        return shareInviteDAO.selectMyShareGroupListCnt(shareInviteVO);
    }
    @Override
    public void insertShareInvite(ShareInviteVO shareInviteVO){
        shareInviteDAO.insertShareInvite(shareInviteVO);
    }
}
