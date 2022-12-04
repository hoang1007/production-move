package vnu.uet.prodmove.services.implement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Customer;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.ProductDetail;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.repos.CustomerRepository;
import vnu.uet.prodmove.services.ICustomerService;
import vnu.uet.prodmove.services.IProductService;
import vnu.uet.prodmove.services.IProductdetailService;
import vnu.uet.prodmove.utils.builder.ProductDetailBuilder;
import vnu.uet.prodmove.utils.querier.ProductDetailQuerier;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductdetailService productDetailService;

    @Override
    public Customer findById(Integer id) throws NotFoundException {
        Optional<Customer> wrapperCustomer = customerRepository.findById(id);
        if (!wrapperCustomer.isPresent()) {
            throw new NotFoundException("Customer is not found.");
        }
        return wrapperCustomer.get();
    }

    @Override
    public void buyProducts(Collection<Product> products, Customer customer) {
        List<ProductDetail> productDetails = new ArrayList<ProductDetail>();
        for (Product product : products) {
            ProductDetailQuerier.of(product).getLast().markCompleted();
            ProductDetail productDetail = ProductDetailBuilder.of(product).sold(customer);
            productDetails.add(productDetail);
            product.setCustomer(customer);
        }
        productService.saveAll(products);
        productDetailService.saveAll(productDetails);
    }
    
}
