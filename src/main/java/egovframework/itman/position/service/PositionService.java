package egovframework.itman.position.service;


import java.util.List;

public interface PositionService {
    List<PositionVO> selectPositionsByGroup(String groIdx);
    List<PositionVO> selectPositionList(PositionVO vo);
    int selectPositionListCnt(PositionVO vo);
    PositionVO selectPositionView(PositionVO positionVO);
    void insertPosition(PositionVO positionVO);
    void updatePosition(PositionVO positionVO);
    void deletePosition(PositionVO positionVO);

}
