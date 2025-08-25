package egovframework.itman.software.service.impl;

import egovframework.itman.software.service.SoftwareService;
import egovframework.itman.software.service.SoftwareVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("softwareService")
public class SoftwareServiceImpl implements SoftwareService {
    private final SoftwareDAO softwareDAO;

    public SoftwareServiceImpl(SoftwareDAO softwareDAO) {
        this.softwareDAO = softwareDAO;
    }

    @Override
    public List<SoftwareVO> selectSoftwareList(String assIdx){
        return softwareDAO.selectSoftwareList(assIdx);
    }
}
