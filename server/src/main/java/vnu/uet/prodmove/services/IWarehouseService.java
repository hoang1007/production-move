package vnu.uet.prodmove.services;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.Warehouse;

public interface IWarehouseService {
    Warehouse create(Warehouse warehouse);

    void storeProductsInWarehouse(Warehouse warehouse, Iterable<Product> products);
}
