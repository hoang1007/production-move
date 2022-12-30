package vnu.uet.prodmove.services.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.enums.ProductStage;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.repos.WarehouseRepository;
import vnu.uet.prodmove.services.IProductdetailService;
import vnu.uet.prodmove.services.IWarehouseService;
import vnu.uet.prodmove.utils.querier.ProductDetailQuerier;

@Service
public class WarehouseService implements IWarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private IProductdetailService productDetailService;

    @Override
    public Warehouse findById(Integer id) throws NotFoundException {
        Optional<Warehouse> wrapperWarehouse = warehouseRepository.findById(id);
        if (wrapperWarehouse.isPresent()) {
            return wrapperWarehouse.get();
        }
        throw new NotFoundException("Warehouse not found.");
    }

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

    @Override
    public List<Product> getAllNewProducts(Integer warehouseId) {
        var warehouse = warehouseRepository.getReferenceById(warehouseId);

        List<Product> products = new ArrayList<>();
        warehouse.getProductdetails().stream().forEach(productDetail -> {
            var product = productDetail.getProduct();

            // Nếu vẫn còn ở trong kho
            if (ProductDetailQuerier.of(product).getLast().getStage().equals(ProductStage.NEW_PRODUCTION)) {
                products.add(product);
            }
        });

        return products;
    }
}
