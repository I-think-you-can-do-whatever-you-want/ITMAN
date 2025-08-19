package egovframework.itman.asset.service.impl;

import egovframework.itman.asset.service.AssetService;
import egovframework.itman.asset.service.AssetVO;
import egovframework.itman.employee.service.EmployeeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("assetService")
public class AssetServiceImpl implements AssetService {
    @Resource(name = "assetDAO")
    private AssetDAO assetDAO;

    @Override
    public List<AssetVO> selectAssetList(AssetVO vo) throws Exception{
        return assetDAO.selectAssetList(vo);
    }

    @Override
    public int selectInGroupAssetListCnt(AssetVO vo) throws Exception{
        return assetDAO.selectInGroupAssetListCnt(vo);
    }

    @Override
    public int selectAssetListCnt(AssetVO vo) throws Exception{
        return assetDAO.selectAssetListCnt(vo);
    }

    @Override
    public List<AssetVO> selectEmpAssetList(EmployeeVO employeeVO) throws Exception{
        return assetDAO.selectEmpAssetList(employeeVO);
    }

    @Override
    public AssetVO selectAssetView(AssetVO vo) {
        return assetDAO.selectAssetView(vo);
    }

    @Override
    public void insertAsset(AssetVO vo) throws Exception {
        assetDAO.insertAsset(vo);
    }

    @Override
    public void updateAsset(AssetVO vo) throws Exception {
        assetDAO.updateAsset(vo);
    }

    @Override
    public void deleteAsset(AssetVO vo) throws Exception {
        assetDAO.deleteAsset(vo);
    }

    @Override
    public void updateAssetNameInfo(AssetVO vo) throws Exception {
         assetDAO.updateAssetNameInfo(vo);
    }

    @Override
    public void updateAssetCategoryInfo(AssetVO vo) throws Exception {
        assetDAO.updateAssetCategoryInfo(vo);
    }

    @Override
    public void updateAssetStateInfo(AssetVO vo) throws Exception {
        assetDAO.updateAssetStateInfo(vo);
    }
    @Override
    public void updateAssetLocationInfo(AssetVO vo) throws Exception {
        assetDAO.updateAssetLocationInfo(vo);
    }

    @Override
    public void updateAssetEmployeeInfo(AssetVO vo) throws Exception {
        assetDAO.updateAssetEmployeeInfo(vo);
    }

    @Override
    public void updateAssetSupplyInfo(AssetVO vo) throws Exception {
        assetDAO.updateAssetSupplyInfo(vo);
    }

    @Override
    public void updateAssetBuyDateInfo(AssetVO vo) throws Exception {
        assetDAO.updateAssetBuyDateInfo(vo);
    }

    @Override
    public void updateAssetPriceInfo(AssetVO vo) throws Exception {
        assetDAO.updateAssetPriceInfo(vo);
    }

    @Override
    public void updateAssetPictureInfo(AssetVO vo) throws Exception{
        assetDAO.updateAssetPictureInfo(vo);
    }
}
