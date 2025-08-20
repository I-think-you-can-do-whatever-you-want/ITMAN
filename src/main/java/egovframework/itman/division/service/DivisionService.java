package egovframework.itman.division.service;

import java.util.List;

public interface DivisionService {
    List<DivisionVO> selectDivisionsByGroup(String groIdx);
    List<DivisionVO> selectDivisionList(DivisionVO vo) throws Exception;
    int selectDivisionListCnt(DivisionVO vo) throws Exception;
    DivisionVO selectDivisionView(DivisionVO divisionVO);
    void insertDivision(DivisionVO divisionVO);
    void updateDivision(DivisionVO divisionVO);
    void deleteDivision(DivisionVO divisionVO);
}