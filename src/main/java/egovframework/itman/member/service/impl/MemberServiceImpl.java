package egovframework.itman.member.service.impl;

import egovframework.itman.member.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Resource(name = "memberDAO")
    private MemberDAO memberDAO;

    @Override
    public boolean existsByEmail(String email){
        return memberDAO.countByEmail(email);
    }
}
