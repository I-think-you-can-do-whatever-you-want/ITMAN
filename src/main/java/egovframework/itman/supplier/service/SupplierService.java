package egovframework.itman.supplier.service;


import java.util.List;

public interface SupplierService {
    List<SupplierVO> selectSuppliersByGroup(String groIdx) throws Exception;
    List<SupplierVO> selectSupplierList(SupplierVO vo) throws Exception;
    int selectSupplierListCnt(SupplierVO vo) throws Exception;
    SupplierVO selectSupplyView(SupplierVO vo) throws Exception;
    void updateSupply(SupplierVO vo) throws Exception;
    void deleteSupply(SupplierVO vo) throws Exception;
    void insertAssetSupplier(SupplierVO vo) throws Exception;
}
