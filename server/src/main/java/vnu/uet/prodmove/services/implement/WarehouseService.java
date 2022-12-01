package vnu.uet.prodmove.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.repos.WarehouseRepository;
import vnu.uet.prodmove.services.IProductdetailService;
import vnu.uet.prodmove.services.IWarehouseService;
import vnu.uet.prodmove.utils.dataModel.WarehouseModel;

@Service
public class WarehouseService implements IWarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private IProductdetailService productdetailService;

    @Override
    public Warehouse create(WarehouseModel warehouse) {
        Warehouse newWarehouse = new Warehouse();
        newWarehouse.setAddress(warehouse.getAddress());
        return warehouseRepository.save(newWarehouse);
    }

    @Override
    public void storeProductsInWarehouse(Warehouse warehouse, Iterable<Product> products) {
        if (warehouse == null) {
            System.out.println("WAREHOUSE IS NULL");
        }

        for (Product product : products) {
            product.getProductDetails().setWarehouse(warehouse);
        }

        productdetailService.saveProducts(products);
    }
    
}
