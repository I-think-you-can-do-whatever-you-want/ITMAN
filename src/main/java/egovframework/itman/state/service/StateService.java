package egovframework.itman.state.service;

import egovframework.itman.common.Pagination;

import java.util.List;

public interface StateService {
    List<StateVO> selectDashBoardAssetStateList(String groIdx) throws Exception;
    List<StateVO> selectAssetStateList(StateVO vo) throws Exception;
    int selectAssetStateListCnt(StateVO vo) throws Exception;
    StateVO selectAssetStateView(StateVO vo) throws Exception;
    List<StateVO> selectStatesByGroup(String groIdx) throws Exception;
    void insertAssetState(StateVO vo) throws Exception;
    void updateAssetState(StateVO vo) throws Exception;
    void deleteAssetState(StateVO vo) throws Exception;
}
