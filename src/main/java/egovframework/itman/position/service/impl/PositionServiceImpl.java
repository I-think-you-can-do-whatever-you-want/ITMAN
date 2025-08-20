package egovframework.itman.position.service.impl;

import egovframework.itman.position.service.PositionService;
import egovframework.itman.position.service.PositionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("positionService")
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionDAO positionDAO;

    @Override
    public List<PositionVO> selectPositionsByGroup(String groIdx) {

        return positionDAO.selectPositionsByGroup(groIdx);
    }

    @Override
    public List<PositionVO> selectPositionList(PositionVO vo) {
        return positionDAO.selectPositionList(vo);
    }

    @Override
    public int selectPositionListCnt(PositionVO vo) {
        return positionDAO.selectPositionListCnt(vo);
    }

    @Override
    public PositionVO selectPositionView(PositionVO positionVO) {
        return positionDAO.selectPositionView(positionVO);
    }

    @Override
    public void insertPosition(PositionVO positionVO) {
        positionDAO.insertPosition(positionVO);
    }

    @Override
    public void updatePosition(PositionVO positionVO) {
        positionDAO.updatePosition(positionVO);
    }

    @Override
    public void deletePosition(PositionVO positionVO) {
        positionDAO.deletePosition(positionVO);
    }
    public boolean isDuplicatePosition(PositionVO vo) {
        int count = positionDAO.checkDuplicate(vo);
        return count > 0;
    }
}
