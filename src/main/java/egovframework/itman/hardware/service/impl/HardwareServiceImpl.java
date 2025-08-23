package egovframework.itman.hardware.service.impl;

import egovframework.itman.hardware.service.HardwareService;
import egovframework.itman.hardware.service.HardwareVO;
import org.springframework.stereotype.Service;

@Service("hardwareService")
public class HardwareServiceImpl implements HardwareService {
    private final HardwareDAO hardwareDAO;

    public HardwareServiceImpl(HardwareDAO hardwareDAO) {
        this.hardwareDAO = hardwareDAO;
    }

    @Override
    public HardwareVO selectHardwareView(String assIdx) throws Exception {
        return hardwareDAO.selectHardwareView(assIdx);
    }
}
