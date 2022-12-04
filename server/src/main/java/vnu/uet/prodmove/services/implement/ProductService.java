package vnu.uet.prodmove.services.implement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.ProductDetail;
import vnu.uet.prodmove.repos.ProductRepository;
import vnu.uet.prodmove.services.IProductService;
import vnu.uet.prodmove.utils.dataModel.ProductModel;

@Service
public class ProductService implements IProductService {
    
    private final int NUMBER_OF_PRODUCTS_PER_PAGE = 10;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Collection<Product> findAllByIds(Collection<Integer> ids) {
        return productRepository.findAllById(ids);
    }

    @Override
    public Collection<Product> findProducts(String filter, int pageNumber, String sortBy, String typeSort) {
        Pageable paging = PageRequest.of(
            (pageNumber - 1) * NUMBER_OF_PRODUCTS_PER_PAGE, // page number
            NUMBER_OF_PRODUCTS_PER_PAGE, // number of products per page
            !typeSort.isEmpty() && !sortBy.isEmpty() ? Sort.by(Direction.fromString(typeSort), sortBy)
            : Sort.unsorted() // sort
        );
            
        Page<Product> pageProducts;

        // Doing filter
        pageProducts = productRepository.findAll(paging);
        if (filter == null) {
        } else {

        }

        return pageProducts.getContent();
    }

    @Override
    public Product create(ProductModel product) {
        return null;
    }

    @Override
    public Product update() {
        return null;
    }

    @Override
    public void destroy() {
        
    }

    @Override
    public Collection<Product> findProductWithAttributes(Collection<String> attributes) {
        // CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        // CriteriaQuery<Product> cq = builder.createQuery(Product.class);
        // Root<Product> root = cq.from(Product.class);
        // Join<ProductDetail, Product> productDetails = root.join("productID");

        // cq.multiselect(root.get("id"), root.get("status"));
        // List<Product> resultList = entityManager.createQuery(cq).getResultList();
        // return resultList;
        return null;
    }

}
