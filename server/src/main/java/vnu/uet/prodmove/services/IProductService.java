package vnu.uet.prodmove.services;

import java.util.Collection;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.utils.dataModel.ProductModel;

public interface IProductService {

    void saveAll(Collection<Product> products);
    
    /**
     * Find all products by list of IDs
     * @param ids
     * @return Collection of products
     */
    Collection<Product> findAllByIds(Collection<Integer> ids);

    /**
     * Create new product
     * @param product
     * @return  new product
     */
    Product create(ProductModel product);

    /**
     * Update a product
     * @return
     */
    Product update();

    /**
     * Destroy a product
     */
    void destroy();

    /**
     * Find all products.
     * @param filter
     * @param pageNumber number of page: 1,2,3,..
     * @param sortBy sort by properties
     * @param typeSort ASC | DESC. Default value is unsorted
     * @return Collection of Products which satisfied all conditions
     */
    Collection<Product> findProducts(String filter, int pageNumber, String sortBy, String typeSort);

}
