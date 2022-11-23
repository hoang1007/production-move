package vnu.uet.prodmove.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.Productdetail;
import vnu.uet.prodmove.repos.ProductRepository;
import vnu.uet.prodmove.utils.converter.json.ProductStageConverter;
import vnu.uet.prodmove.utils.productStage.NewProductionStage;

@Service
public class ManufactoringBaseService {
    @Autowired
    private ProductRepository productRepository;

    public void importProducts(List<Product> products, Integer warehouseId) {
        for (var product : products) {
            Productdetail detail = ProductStageConverter.toProductDetail(new NewProductionStage(warehouseId));

            product.getProductDetails().add(detail);
        }

        productRepository.saveAll(products);
    }

    public void exportToAgency() {

    }
}