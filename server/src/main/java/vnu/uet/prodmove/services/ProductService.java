package vnu.uet.prodmove.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.repos.ProductRepository;

@Service
public class ProductService {
    private final int NUMBER_OF_PRODUCTS_PER_PAGE = 10;

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findProducts(String filter, int pageNumber, String sortBy, String typeSort) {

        List<Product> products = new ArrayList<>();
        Pageable paging = PageRequest.of(
            (pageNumber - 1) * NUMBER_OF_PRODUCTS_PER_PAGE,                                          // page number
            NUMBER_OF_PRODUCTS_PER_PAGE,                                                            // number of products per page
            !typeSort.isEmpty() && !sortBy.isEmpty()? Sort.by(Direction.fromString(typeSort), sortBy) : Sort.unsorted()  // sort
        );
        
        Page<Product> pageProducts;
        
        // do filter
        pageProducts = productRepository.findAll(paging);
        if (filter == null) {
        } else {

        }
        products = pageProducts.getContent();

        return products;
    }
}
