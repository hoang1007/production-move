package vnu.uet.prodmove.services;

import java.util.Collection;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.utils.dataModel.WarehouseModel;

public interface IWarehouseService {
    Warehouse create(WarehouseModel warehouse);

    void storeProductsInWarehouse(Warehouse warehouse, Iterable<Product> products);
}
