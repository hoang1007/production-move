package vnu.uet.prodmove.services.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import vnu.uet.prodmove.entity.WarrantyCenter;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.repos.ProductRepository;
import vnu.uet.prodmove.repos.WarrantyCenterRepository;
import vnu.uet.prodmove.services.IWarrantyCenterService;
import vnu.uet.prodmove.utils.builder.ProductDetailBuilder;

public class WarrantyCenterService implements IWarrantyCenterService {

    @Autowired
    private WarrantyCenterRepository warrantyCenterRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public WarrantyCenter findById(Integer id) throws NotFoundException {
        Optional<WarrantyCenter> wrapperWarrantyCenter = warrantyCenterRepository.findById(id);
        if(wrapperWarrantyCenter.isPresent()) {
            return wrapperWarrantyCenter.get();
        }
        throw new NotFoundException("WarrantyCenter not found.");
    }

    @Override
    public void receiveProductsFromAgency(Iterable<Integer> productIds, Integer agencyId, Integer warrantyCenterId) {
        var products = productRepository.findAllById(productIds);

        for (var product : products) {
            product.addProductDetail(ProductDetailBuilder.of(product).repairing());
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
