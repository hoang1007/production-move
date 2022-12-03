package vnu.uet.prodmove.services.implement;

import org.springframework.beans.factory.annotation.Autowired;

import vnu.uet.prodmove.repos.ProductRepository;
import vnu.uet.prodmove.services.IWarrantyCenterService;
import vnu.uet.prodmove.utils.builder.ProductDetailBuilder;

public class WarrantyCenterService implements IWarrantyCenterService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void receiveProductsFromAgency(Iterable<Integer> productIds, Integer agencyId, Integer warrantyCenterId) {
        var products = productRepository.findAllById(productIds);

        for (var product : products) {
            product.addProductDetail(ProductDetailBuilder.repairing(product));
        }
    }

    @Override
    public void returnProductsToAgency(Iterable<Integer> productIds, Integer agencyId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void returnProductsToFactory(Iterable<Integer> productIds, Integer factoryId) {
        // TODO Auto-generated method stub

    }

}
