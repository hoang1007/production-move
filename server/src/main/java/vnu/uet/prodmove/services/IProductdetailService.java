package vnu.uet.prodmove.services;

import java.util.Collection;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.ProductDetail;

public interface IProductdetailService {
    void saveProducts(Iterable<Product> products);

    ProductDetail createProductDetail(ProductDetail productDetail);

    Collection<ProductDetail> saveAll(Collection<ProductDetail> productdetails);
}
