package vnu.uet.prodmove.services.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.ProductDetail;
import vnu.uet.prodmove.entity.WarrantyCenter;
import vnu.uet.prodmove.enums.ProductStage;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.repos.ProductRepository;
import vnu.uet.prodmove.repos.ProductdetailRepository;
import vnu.uet.prodmove.repos.WarrantyCenterRepository;
import vnu.uet.prodmove.services.IWarrantyCenterService;
import vnu.uet.prodmove.utils.builder.ProductDetailBuilder;
import vnu.uet.prodmove.utils.querier.ProductDetailQuerier;

@Service
public class WarrantyCenterService implements IWarrantyCenterService {

    @Autowired
    private WarrantyCenterRepository warrantyCenterRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductdetailRepository productdetailRepository;

    @Override
    public List<WarrantyCenter> findAll() {
        return warrantyCenterRepository.findAll();
    }

    @Override
    public WarrantyCenter findById(Integer id) throws NotFoundException {
        Optional<WarrantyCenter> wrapperWarrantyCenter = warrantyCenterRepository.findById(id);
        if (wrapperWarrantyCenter.isPresent()) {
            return wrapperWarrantyCenter.get();
        }
        throw new NotFoundException("WarrantyCenter not found.");
    }

    @Override
    public void delete(Integer id) {
        warrantyCenterRepository.deleteById(id);
    }

    @Override
    public void receiveProductsFromAgency(Iterable<Integer> productIds) {
        var products = productRepository.findAllById(productIds);
        var newDetails = new ArrayList<ProductDetail>();

        for (var product : products) {
            var needRepair = ProductDetailQuerier.of(product).getLast();

            if (needRepair.getStage() == ProductStage.NEED_REPAIR && needRepair != null && !needRepair.completed()) {
                needRepair.markCompleted();

                newDetails.add(ProductDetailBuilder.of(product).repairing());
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
            newDetails.add(ProductDetailBuilder.of(product).repaired());
        }

        productdetailRepository.saveAll(newDetails);
    }

    @Override
    public void returnProductsToFactory(Iterable<Integer> productIds) {
        var products = productRepository.findAllById(productIds);

        var newDetails = new ArrayList<ProductDetail>();

        for (var product : products) {
            newDetails.add(ProductDetailBuilder.of(product).needReturnToFactory());
        }

        productdetailRepository.saveAll(newDetails);
    }

    @Override
    public void annouceCannotRepairProducts(Iterable<Integer> productIds) {
        throw new NotYetImplementedException();
        // var products = productRepository.findAllById(productIds);

        // for (var product : products) {
        // var repairing = ProductDetailQuerier.of(product).getLast();

        // if (repairing.getStage() == ProductStage.REPAIRING && repairing != null &&
        // !repairing.completed()) {
        // repairing.markCompleted();
        // ProductDetailBuilder.of(product).needReturnToFactory(repairing.getWarrantyCenter());
        // } else {
        // throw new RuntimeException("Product is not in repairing stage");
        // }
        // }

        // productRepository.saveAll(products);
    }

    @Override
    public List<ProductDetail> getNeedRepairProducts() {
        return productdetailRepository.findAll().stream().filter(detail -> {
            return detail.getStage().equals(ProductStage.NEED_REPAIR)
                    && !detail.completed();
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProductDetail> getRepairingProducts(Integer warrantyCenterId) {
        return productdetailRepository.findAll().stream().filter(detail -> {
            return detail.getStage().equals(ProductStage.REPAIRING)
                    && !detail.completed()
                    && detail.getWarrantyCenter().getId().equals(warrantyCenterId);
        }).collect(Collectors.toList());
    }

    @Override
    public WarrantyCenter create(String name, String address) {
        WarrantyCenter warrantyCenter = new WarrantyCenter();
        warrantyCenter.setName(name);
        warrantyCenter.setAddress(address);
        return warrantyCenterRepository.save(warrantyCenter);
    }
}
