package egovframework.itman.employee.service.impl;

import egovframework.itman.employee.service.EmployeeVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAO  {
    @Autowired
    private SqlSession sqlSession;

    public List<EmployeeVO> selectEmployeeList(EmployeeVO vo) throws Exception {

        return sqlSession.selectList("employeeDAO.selectEmployeeList", vo);
    }

    public int selectEmployeeListCnt(EmployeeVO employeeVO) throws Exception{
        return sqlSession.selectOne("employeeDAO.selectEmployeeListCnt", employeeVO);
    }

    public EmployeeVO selectEmployeeView(EmployeeVO vo) {
        return sqlSession.selectOne("employeeDAO.selectEmployeeView", vo);
    }

    public void insertEmployee(EmployeeVO vo) {
        sqlSession.insert("employeeDAO.insertEmployee", vo);
    }

    public void deleteEmployee(EmployeeVO vo) {
        sqlSession.update("employeeDAO.deleteEmployee", vo);
    }

    public void updateEmployee(EmployeeVO vo) {

        sqlSession.update("employeeDAO.updateEmployee", vo);
    }

    //update functions

    public void updateEmploTelInfo(EmployeeVO vo){
        sqlSession.update("employeeDAO.updateEmploTelInfo", vo);
    }
    public void updateEmploNumInfo(EmployeeVO vo){
        sqlSession.update("employeeDAO.updateEmploNumInfo", vo);
    }
    public void updateEmploDivisionInfo(EmployeeVO vo){
        sqlSession.update("employeeDAO.updateEmploDivisionInfo", vo);
    }
    public void updateEmploPosInfo(EmployeeVO vo){
        sqlSession.update("employeeDAO.updateEmploPosInfo", vo);
    }
    public void updateEmploMailInfo(EmployeeVO vo){
        sqlSession.update("employeeDAO.updateEmploMailInfo", vo);
    }
    public void updateEmploNameInfo(EmployeeVO vo){
        sqlSession.update("employeeDAO.updateEmploNameInfo", vo);
    }
    public void updateEmploStateInfo(EmployeeVO vo){
        sqlSession.update("employeeDAO.updateEmploStateInfo", vo);
    }
}
