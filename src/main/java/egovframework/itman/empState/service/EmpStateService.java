package egovframework.itman.empState.service;

import java.util.List;

public interface EmpStateService {
    List<EmpStateVO> selectEmpStatesByGroup(String groIdx);
    List<EmpStateVO> selectEmpStateList(EmpStateVO vo) throws Exception;
    int selectEmpStateListCnt(EmpStateVO vo) throws Exception;
    EmpStateVO selectEmpStateView(EmpStateVO vo) throws Exception;
    void insertEmployeeState(EmpStateVO vo);
    void updateEmployeeState(EmpStateVO vo);
    void deleteEmployeeState(EmpStateVO vo);
}
