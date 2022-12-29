package vnu.uet.prodmove.services;

import java.util.Collection;
import java.util.List;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.ProductDetail;

public interface IProductdetailService {
    List<ProductDetail> findAll();

    ProductDetail getProductDetail(Integer id);

    void saveProducts(Iterable<Product> products);

    ProductDetail createProductDetail(ProductDetail productDetail);

    Collection<ProductDetail> saveAll(Collection<ProductDetail> productdetails);

    Collection<ProductDetail> findPendingProductDetails(Integer agencyId);

}
