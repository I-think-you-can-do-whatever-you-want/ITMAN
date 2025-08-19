package egovframework.itman.asset.service;

import egovframework.itman.employee.service.EmployeeVO;

import java.util.List;

public interface AssetService {
    List<AssetVO> selectAssetList(AssetVO vo) throws Exception;
    int selectAssetListCnt(AssetVO vo) throws Exception;
    int selectInGroupAssetListCnt(AssetVO vo) throws Exception;
    List<AssetVO> selectEmpAssetList(EmployeeVO employeeVO) throws Exception;
    AssetVO selectAssetView(AssetVO vo);

    void insertAsset(AssetVO vo) throws Exception;

    void updateAsset(AssetVO vo) throws Exception;
    void updateAssetNameInfo(AssetVO vo) throws Exception;
    void updateAssetCategoryInfo(AssetVO vo) throws Exception;
    void updateAssetStateInfo(AssetVO vo) throws Exception;
    void updateAssetLocationInfo(AssetVO vo) throws Exception;
    void updateAssetEmployeeInfo(AssetVO vo) throws Exception;
    void updateAssetSupplyInfo(AssetVO vo) throws Exception;
    void updateAssetBuyDateInfo(AssetVO vo) throws Exception;
    void updateAssetPriceInfo(AssetVO vo) throws Exception;
    void updateAssetPictureInfo(AssetVO vo) throws Exception;
    void deleteAsset(AssetVO vo) throws Exception;

}
