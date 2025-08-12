package egovframework.itman.member.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
    @Autowired
    SqlSession sqlSession;

    public boolean countByEmail(String email){
        Integer count = (Integer) sqlSession.selectOne("memberDAO.countByEmail", email);
        return count != null && count > 0;    }
}
