package vnu.uet.prodmove.services;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.exception.NotFoundException;

public interface IWarehouseService {
    Warehouse findById(Integer id) throws NotFoundException;

    Warehouse create(Warehouse warehouse);

    void storeProductsInWarehouse(Warehouse warehouse, Iterable<Product> products);
}
