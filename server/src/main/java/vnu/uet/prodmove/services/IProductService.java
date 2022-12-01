package vnu.uet.prodmove.services;

import java.util.Collection;
import java.util.List;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.utils.dataModel.ProductModel;

public interface IProductService {
    Product create(ProductModel product);

    Product update();

    void destroy();

    List<Product> findProducts(String filter, int pageNumber, String sortBy, String typeSort);

    Collection<Product> findAllProductsById(Collection<Integer> ids);

    List<Product> findProductWithAttributes();

}
