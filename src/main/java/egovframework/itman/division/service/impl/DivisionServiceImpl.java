package egovframework.itman.division.service.impl;

import egovframework.itman.division.service.DivisionService;
import egovframework.itman.division.service.DivisionVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("divisionService")
public class DivisionServiceImpl implements DivisionService {
    private final DivisionDAO divisionDAO;

    public DivisionServiceImpl(DivisionDAO divisionDAO) {
        this.divisionDAO = divisionDAO;
    }

    public List<DivisionVO> selectDivisionsByGroup(String groIdx) {

        return divisionDAO.selectDivisionsByGroup(groIdx);
    }

    @Override
    public List<DivisionVO> selectDivisionList(DivisionVO vo) throws Exception {
        return divisionDAO.selectDivisionList(vo);
    }

    @Override
    public int selectDivisionListCnt(DivisionVO vo) throws Exception {
        return divisionDAO.selectDivisionListCnt(vo);
    }

    @Override
    public DivisionVO selectDivisionView(DivisionVO divisionVO) {
        return divisionDAO.selectDivisionView(divisionVO);
    }

    @Override
    public void insertDivision(DivisionVO vo) {
         divisionDAO.insertDivision(vo);
    }

    @Override
    public void updateDivision(DivisionVO divisionVO) {
        divisionDAO.updateDivision(divisionVO);
    }

    @Override
    public void deleteDivision(DivisionVO divisionVO) {
        divisionDAO.deleteDivision(divisionVO);
    }

    public boolean isDuplicateDivision(DivisionVO vo){
        int count = divisionDAO.checkDuplicate(vo);
        return count > 0;
    }
}
