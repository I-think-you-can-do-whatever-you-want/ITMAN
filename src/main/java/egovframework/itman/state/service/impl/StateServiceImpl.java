package egovframework.itman.state.service.impl;

import egovframework.itman.common.Pagination;
import egovframework.itman.state.service.StateService;
import egovframework.itman.state.service.StateVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("stateService")
public class StateServiceImpl implements StateService {
    @Resource(name = "stateDAO")
    private StateDAO stateDAO;

    @Override
    public List<StateVO> selectAssetStateList(StateVO vo) throws Exception {
        return stateDAO.selectAssetStateList(vo);
    }

    @Override
    public int selectAssetStateListCnt(StateVO vo) throws Exception {
        return stateDAO.selectAssetStateListCnt(vo);
    }
    @Override
    public List<StateVO> selectDashBoardAssetStateList(String groIdx) throws Exception{
        return stateDAO.selectDashBoardAssetStateList(groIdx);
    }

    @Override
    public StateVO selectAssetStateView(StateVO vo) throws Exception{
        return stateDAO.selectAssetStateView(vo);
    }
    @Override
    public List<StateVO> selectStatesByGroup(String groIdx) throws Exception {
        return stateDAO.selectStatesByGroup(groIdx);
    }

    @Override
    public void insertAssetState(StateVO vo) throws Exception {
        stateDAO.insertAssetState(vo);
    }

    @Override
    public void updateAssetState(StateVO vo) throws Exception {
        stateDAO.updateAssetState(vo);
    }

    @Override
    public void deleteAssetState(StateVO vo) throws Exception {
        stateDAO.deleteAssetState(vo);
    }

    public boolean isDuplicateState(StateVO vo){
        int count = stateDAO.checkDuplicate(vo);
        return count > 0;
    }
}
