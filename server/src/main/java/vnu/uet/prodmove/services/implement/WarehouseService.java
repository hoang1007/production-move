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
    private IProductdetailService productDetailService;

    @Override
    public Warehouse create(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    @Override
    public void storeProductsInWarehouse(Warehouse warehouse, Iterable<Product> products) {
        if (warehouse == null) {
            System.out.println("WAREHOUSE IS NULL");
            throw new NullPointerException("warehouse is not null.");
        }

        for (Product product : products) {
            product.getProductDetails().iterator().next().setWarehouse(warehouse);
        }

        productDetailService.saveProducts(products);
    }
    
}
