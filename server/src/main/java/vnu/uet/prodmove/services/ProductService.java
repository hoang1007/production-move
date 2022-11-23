package vnu.uet.prodmove.services;

import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.utils.converter.json.ProductStageConverter;
import vnu.uet.prodmove.utils.productStage.NewProductionStage;
import vnu.uet.prodmove.utils.productStage.ProductStageType;

@Service
public class ProductService {    
    public NewProductionStage getNewProductionStage(Product product) {
        for (var detail : product.getProductDetails()) {
            if (detail.getStage().equals(ProductStageType.NEW_PRODUCTION)) {
                return ProductStageConverter.fromProductDetail(detail, NewProductionStage.class);
            }
        }

        return null;
    }
}
