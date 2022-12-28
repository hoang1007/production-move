package vnu.uet.prodmove.services.implement;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.ProductDetail;
import vnu.uet.prodmove.entity.Productline;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.enums.ProductStage;
import vnu.uet.prodmove.repos.AgencyRepository;
import vnu.uet.prodmove.repos.ProductRepository;
import vnu.uet.prodmove.repos.ProductdetailRepository;
import vnu.uet.prodmove.repos.ProductlineRepository;
import vnu.uet.prodmove.repos.WarehouseRepository;
import vnu.uet.prodmove.services.IManufactoringBaseService;
import vnu.uet.prodmove.utils.builder.ProductDetailBuilder;
import vnu.uet.prodmove.utils.querier.ProductDetailQuerier;

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

    @Autowired
    private ProductdetailRepository productDetailRepository;

    @Autowired
    private SessionFactory sessionFactory;

    public void importProducts(Integer productlineId, Integer quantity, Integer warehouseId) {
        Warehouse warehouse = warehouseRepository.getReferenceById(warehouseId);
        Productline productline = productlineRepository.getReferenceById(productlineId);

        Session session = sessionFactory.openSession();

        // List<Product> products = new ArrayList<Product>();

        for (int i = 0; i < quantity; i++) {
            Product product = new Product();
            product.setProductline(productline);

            ProductDetail newProd = ProductDetailBuilder.of(product).newProduction(warehouse);
            product.addProductDetail(newProd);
            
            session.save(product);
            session.save(newProd);
        }

        // productRepository.saveAll(products);
        // session.getTransaction().commit();
        session.close();
    }

    @Override
    public void exportToAgency(Iterable<Integer> productIds, Integer agencyId) {
        Agency agency = agencyRepository.getReferenceById(agencyId);
        
        List<Product> products = productRepository.findAllById(productIds);
        List<ProductDetail> productDetails = new ArrayList<ProductDetail>();
    
        for (Product product : products) {
            productDetails.add(ProductDetailBuilder.of(product).exportToAgency(agency));
        }

        productDetailRepository.saveAll(productDetails);
    }

    @Override
    public void receiveReturnedProducts(Iterable<Integer> productIds) {
        List<Product> products = productRepository.findAllById(productIds);
        List<ProductDetail> productDetails = new ArrayList<ProductDetail>();
        
        for (Product product : products) {
            ProductDetail returned = new ProductDetailQuerier(product).getLast();

            if (returned.getStage() == ProductStage.RETURNED_TO_FACTORY && !returned.completed()) {
                returned.markCompleted();
                productDetails.add(returned);
            } else {
                throw new RuntimeException("Product is not returned to factory");
            }
        }

        productDetailRepository.saveAll(productDetails);
    }
}