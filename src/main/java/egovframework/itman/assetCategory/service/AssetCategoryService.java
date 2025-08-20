package egovframework.itman.assetCategory.service;

import java.util.List;

public interface AssetCategoryService {
    List<AssetCategoryVO> selectAssetCategoriesByGroup(String groIdx) throws Exception;
    List<AssetCategoryVO> selectAssetCategoryList(AssetCategoryVO vo) throws Exception;
    int selectAssetCategoryListCnt(AssetCategoryVO vo) throws Exception;
    AssetCategoryVO selectAssetCategory(AssetCategoryVO vo) throws Exception;
    void insertAssetCategory(AssetCategoryVO vo) throws Exception;
    void updateAssetCategory(AssetCategoryVO vo) throws Exception;
    void deleteAssetCategory(AssetCategoryVO vo) throws Exception;
    List<AssetCategoryVO> findAll(String groIdx) throws Exception;

}
