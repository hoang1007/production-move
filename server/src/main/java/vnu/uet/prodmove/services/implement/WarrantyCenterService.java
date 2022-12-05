package vnu.uet.prodmove.services.implement;

import java.util.ArrayList;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.ProductDetail;
import vnu.uet.prodmove.enums.ProductStage;
import vnu.uet.prodmove.repos.FactoryRepository;
import vnu.uet.prodmove.repos.ProductRepository;
import vnu.uet.prodmove.repos.ProductdetailRepository;
import vnu.uet.prodmove.services.IWarrantyCenterService;
import vnu.uet.prodmove.utils.builder.ProductDetailBuilder;
import vnu.uet.prodmove.utils.querier.ProductDetailQuerier;

@Service
public class WarrantyCenterService implements IWarrantyCenterService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FactoryRepository factoryRepository;

    @Autowired
    private ProductdetailRepository productdetailRepository;

    @Override
    public void receiveProductsFromAgency(Iterable<Integer> productIds) {
        var products = productRepository.findAllById(productIds);
        var newDetails = new ArrayList<ProductDetail>();

        for (var product : products) {
            var needRepair = ProductDetailQuerier.of(product).getLast();

            if (needRepair.getStage() == ProductStage.NEED_REPAIR && needRepair != null && !needRepair.completed()) {
                needRepair.markCompleted();

                newDetails.add(ProductDetailBuilder.of(product).repairing(needRepair.getWarrantyCenter()));
            } else {
                throw new RuntimeException("Product is not in need repair stage");
            }
        }

        productdetailRepository.saveAll(newDetails);
    }

    @Override
    public void returnProductsToAgency(Iterable<Integer> productIds) {
        var products = productRepository.findAllById(productIds);
        var newDetails = new ArrayList<ProductDetail>();

        for (var product : products) {
            var querier = ProductDetailQuerier.of(product);
            var repairing = querier.getLast();

            if (repairing.getStage() == ProductStage.REPAIRING && repairing != null && !repairing.completed()) {
                repairing.markCompleted();

                var agency = querier.filter(ProductStage.NEED_REPAIR).getLast().getAgency();
                newDetails.add(ProductDetailBuilder.of(product).repaired(repairing.getWarrantyCenter(), agency));
            } else {
                throw new RuntimeException("Product is not in repairing stage");
            }
        }

        productdetailRepository.saveAll(newDetails);
    }

    @Override
    public void returnProductsToFactory(Iterable<Integer> productIds, Integer factoryId) {
        var products = productRepository.findAllById(productIds);
        var factory = factoryRepository.getReferenceById(factoryId);

        var newDetails = new ArrayList<ProductDetail>();

        for (var product : products) {
            var querier = ProductDetailQuerier.of(product);
            var needReturn = querier.getLast();

            if (needReturn.getStage() == ProductStage.REPAIRING && needReturn != null && !needReturn.completed()) {
                needReturn.markCompleted();
                newDetails.add(ProductDetailBuilder.of(product).returnedToFactory(factory));
            } else {
                throw new RuntimeException("Product is not in need return to factory stage");
            }
        }

        productdetailRepository.saveAll(newDetails);
    }

    @Override
    public void annouceCannotRepairProducts(Iterable<Integer> productIds) {
        throw new NotYetImplementedException();
        // var products = productRepository.findAllById(productIds);

        // for (var product : products) {
        //     var repairing = ProductDetailQuerier.of(product).getLast();

        //     if (repairing.getStage() == ProductStage.REPAIRING && repairing != null && !repairing.completed()) {
        //         repairing.markCompleted();
        //         ProductDetailBuilder.of(product).needReturnToFactory(repairing.getWarrantyCenter());
        //     } else {
        //         throw new RuntimeException("Product is not in repairing stage");
        //     }
        // }

        // productRepository.saveAll(products);
    }
}
