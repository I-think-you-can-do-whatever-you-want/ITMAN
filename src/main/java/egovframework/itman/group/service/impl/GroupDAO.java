package egovframework.itman.group.service.impl;

import egovframework.itman.group.service.GroupVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDAO {
    @Autowired
    SqlSession sqlSession;

    public GroupVO getHeaderData(String groIdx) throws Exception{
      return sqlSession.selectOne("groupDAO.getHeaderData", groIdx);
    }

    public List<GroupVO> selectGroupList(String memIdx) throws Exception{
        return sqlSession.selectList("groupDAO.selectGroupList", memIdx);
    }

    public List<GroupVO> selectGroupListNotShare(String memIdx){
        return sqlSession.selectList("groupDAO.selectGroupListNotShare", memIdx);
    }

    public List<GroupVO> getAllGroupData(String memIdx) throws Exception{
        return sqlSession.selectList("groupDAO.getAllGroupData", memIdx);
    }
    public void insertGroup(GroupVO vo) throws Exception{
        sqlSession.insert("groupDAO.insertGroup", vo);
    }

    public void updateGroup(GroupVO vo) throws Exception{
        sqlSession.update("groupDAO.updateGroup", vo);
    }

    public void deleteGroup(GroupVO vo) throws Exception{
        sqlSession.update("groupDAO.deleteGroup", vo);
    }
    public GroupVO selectGroup(String groIdx){
        return sqlSession.selectOne("groupDAO.selectGroup", groIdx);
    }
}
