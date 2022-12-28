package vnu.uet.prodmove.services.implement;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.ProductDetail;
import vnu.uet.prodmove.repos.ProductdetailRepository;
import vnu.uet.prodmove.services.IProductdetailService;

@Service
public class ProductdetailService implements IProductdetailService{

    @Autowired
    private ProductdetailRepository productdetailRepository;

    // @Override
    public void saveProducts(Iterable<Product> products) {
        // productdetailRepository.saveAll(((List<Product>)products).stream().map(product -> product.getProductDetails()).collect(Collectors.toList()));
    }

    @Override
    public ProductDetail createProductDetail(ProductDetail productDetail) {
        return productdetailRepository.save(productDetail);
    }

    @Override
    public Collection<ProductDetail> saveAll(Collection<ProductDetail> productdetails) {
        return productdetailRepository.saveAll(productdetails);
    }
}
