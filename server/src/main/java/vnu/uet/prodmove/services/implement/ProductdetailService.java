package vnu.uet.prodmove.services.implement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.ProductDetail;
import vnu.uet.prodmove.enums.ProductStage;
import vnu.uet.prodmove.repos.ProductdetailRepository;
import vnu.uet.prodmove.services.IProductdetailService;

@Service
public class ProductdetailService implements IProductdetailService {

    @Autowired
    private ProductdetailRepository productdetailRepository;

    // @Override
    public void saveProducts(Iterable<Product> products) {
        // productdetailRepository.saveAll(((List<Product>)products).stream().map(product
        // -> product.getProductDetails()).collect(Collectors.toList()));
    }

    @Override
    public ProductDetail createProductDetail(ProductDetail productDetail) {
        return productdetailRepository.save(productDetail);
    }

    @Override
    public Collection<ProductDetail> saveAll(Collection<ProductDetail> productdetails) {
        return productdetailRepository.saveAll(productdetails);
    }

    @Override
    public ProductDetail getProductDetail(Integer id) {
        return productdetailRepository.getReferenceById(id);
    }

    @Override
    public Collection<ProductDetail> findPendingProductDetails(Integer agencyId) {
        List<ProductDetail> productDetailsBelongsToAgency = (List<ProductDetail>) productdetailRepository
                .findByAgencyID(agencyId);
                List<ProductDetail> pendingProductDetails = new ArrayList<>();
                Map<Integer, ProductDetail> temp = new HashMap<>();
                for (ProductDetail p : productDetailsBelongsToAgency) {
                    temp.put(p.getProduct().getId(), p);
                }
                for (Integer key : temp.keySet()) {
                    pendingProductDetails.add(temp.get(key));
                }
        return pendingProductDetails.stream().filter(p -> !p.completed()).collect(Collectors.toList());
    }

    @Override
    public List<ProductDetail> findAll() {
        return productdetailRepository.findAll();
    }
}
