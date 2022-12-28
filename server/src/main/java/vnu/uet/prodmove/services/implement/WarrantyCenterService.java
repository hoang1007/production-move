package vnu.uet.prodmove.services.implement;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.ProductDetail;
import vnu.uet.prodmove.entity.WarrantyCenter;
import vnu.uet.prodmove.enums.ProductStage;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.repos.FactoryRepository;
import vnu.uet.prodmove.repos.ProductRepository;
import vnu.uet.prodmove.repos.ProductdetailRepository;
import vnu.uet.prodmove.repos.WarrantyCenterRepository;
import vnu.uet.prodmove.services.IWarrantyCenterService;
import vnu.uet.prodmove.utils.builder.ProductDetailBuilder;

@Service
public class WarrantyCenterService implements IWarrantyCenterService {

    @Autowired
    private WarrantyCenterRepository warrantyCenterRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductdetailRepository productdetailRepository;

    @Override
    public WarrantyCenter findById(Integer id) throws NotFoundException {
        Optional<WarrantyCenter> wrapperWarrantyCenter = warrantyCenterRepository.findById(id);
        if (wrapperWarrantyCenter.isPresent()) {
            return wrapperWarrantyCenter.get();
        }
        throw new NotFoundException("WarrantyCenter not found.");
    }

    @Override
    public void receiveProductsFromAgency(Iterable<Integer> productIds) {
        var products = productRepository.findAllById(productIds);
        var newDetails = new ArrayList<ProductDetail>();

        for (var product : products) {
            newDetails.add(ProductDetailBuilder.of(product).repairing());
        }

        productdetailRepository.saveAll(newDetails);
    }

    @Override
    public void returnProductsToAgency(Iterable<Integer> productIds) {
        var products = productRepository.findAllById(productIds);
        var newDetails = new ArrayList<ProductDetail>();

        for (var product : products) {
            newDetails.add(ProductDetailBuilder.of(product).repaired());
        }

        productdetailRepository.saveAll(newDetails);
    }

    @Override
    public void returnProductsToFactory(Iterable<Integer> productIds, Integer factoryId) {
        var products = productRepository.findAllById(productIds);

        var newDetails = new ArrayList<ProductDetail>();

        for (var product : products) {
            newDetails.add(ProductDetailBuilder.of(product).needReturnToFactory());
        }

        productdetailRepository.saveAll(newDetails);
    }
}
