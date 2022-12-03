package vnu.uet.prodmove.services.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.Productline;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.repos.AgencyRepository;
import vnu.uet.prodmove.repos.ProductRepository;
import vnu.uet.prodmove.repos.ProductlineRepository;
import vnu.uet.prodmove.repos.WarehouseRepository;
import vnu.uet.prodmove.services.IManufactoringBaseService;
import vnu.uet.prodmove.utils.builder.ProductDetailBuilder;

@Service
public class ManufactoringBaseService implements IManufactoringBaseService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductlineRepository productlineRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    public void importProducts(Integer productlineId, Integer quantity, Integer warehouseId) {
        Warehouse warehouse = warehouseRepository.getReferenceById(warehouseId);
        Productline productline = productlineRepository.getReferenceById(productlineId);
        List<Product> products = new ArrayList<Product>();

        for (int i = 0; i < quantity; i++) {
            Product product = new Product();

            product.setProductline(productline);
            product.addProductDetail(ProductDetailBuilder.newProduction(warehouse));
            
            products.add(product);
        }

        productRepository.saveAll(products);
    }

    @Override
    public void exportToAgency(Iterable<Integer> productIds, Integer agencyId) {
        Agency agency = agencyRepository.getReferenceById(agencyId);
        
        List<Product> products = productRepository.findAllById(productIds);
    
        for (Product product : products) {
            product.addProductDetail(ProductDetailBuilder.exportToAgency(agency));

            products.add(product);
        }

        productRepository.saveAll(products);
    }
}