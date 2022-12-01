package vnu.uet.prodmove.services;

import java.util.Collection;
import java.util.Set;

import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.utils.dataModel.WarehouseModel;

public interface IAgencyService {
    Agency findById(Integer id);

    void storeProductsInWarehouse(int agencyId, int warehouseId, Collection<Integer> productIds)
            throws NotFoundException;
    
    void createWarehouse(Agency agency, WarehouseModel warehouseModel);

    Set<Warehouse> getAllWarehouses(Integer agencyId) throws NotFoundException;

    void saleProductsById(Collection<Integer> productIds);
}
