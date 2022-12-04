package vnu.uet.prodmove.services;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Set;

import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.utils.dataModel.WarehouseModel;

public interface IAgencyService {

    Agency findById(Integer id) throws NotFoundException;

    Warehouse createWarehouse(Integer agencyId, WarehouseModel warehouseModel) throws Exception;

    /**
     * Import pending products into a warehouse of agency.
     * 
     * @param agencyId    ID of the agency
     * @param warehouseId ID of the warehouse
     * @param productIds  list ID of the pending products
     * @throws NotFoundException
     */
    void importPendingProductsFromFactory(Integer agencyId, Integer warehouseId, Collection<String> productIds) throws NotFoundException;

    /**
     * Get all warehouses of specific agency
     * @param agencyId ID of agency
     * @return Collection of warehouses
     * @throws NotFoundException
     */
    Collection<Warehouse> getAllWarehouses(Integer agencyId) throws NotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
    
    /**
     * Sell products to customer.
     * @param customerId ID of the customer
     * @param productIds Collection of product IDs.
     */
    void sellProducts(Integer customerId, Collection<Integer> productIds) throws NotFoundException;

}
