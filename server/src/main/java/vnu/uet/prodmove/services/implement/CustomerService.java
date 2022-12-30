package vnu.uet.prodmove.services.implement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Customer;
import vnu.uet.prodmove.entity.Order;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.ProductDetail;
import vnu.uet.prodmove.enums.ProductStage;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.repos.CustomerRepository;
import vnu.uet.prodmove.services.ICustomerService;
import vnu.uet.prodmove.services.IOrderService;
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

    @Autowired
    private IOrderService orderService;

    @Override
    public Customer findById(Integer id) throws NotFoundException {
        Optional<Customer> wrapperCustomer = customerRepository.findById(id);
        if (!wrapperCustomer.isPresent()) {
            throw new NotFoundException("Customer is not found.");
        }
        return wrapperCustomer.get();
    }

    @Override
    public void buyProducts(Collection<Product> products, Customer customer) throws CloneNotSupportedException {
        List<ProductDetail> productDetails = new ArrayList<ProductDetail>();
        List<Order> orders = new ArrayList<>();
        for (Product product : products) {
            Order o = product.getOrder();
            o.setSoldDate(LocalDateTime.now());
            orders.add(o);

            ProductDetail productDetail = ProductDetailQuerier.of(product).getLast();
            productDetail.setStage(ProductStage.SOLD);
            productDetail.markCompleted();
            productDetail.setCustomer(customer);
            productDetails.add(productDetail);
            // product.setCustomer(customer);
        }
        productDetailService.saveAll(productDetails);
        orderService.saveAll(orders);
    }

    @Override
    public void orderProducts(Collection<Integer> productIds, Integer customerId) throws NotFoundException {
        Customer customer = findById(customerId);
        var products = productService.findAllByIds(productIds);

        var orders = new ArrayList<Order>();
        var details = new ArrayList<ProductDetail>();

        for (Product product : products) {
            var querier = ProductDetailQuerier.of(product);

            if (querier.getLast().getStage() != ProductStage.EXPORT_TO_AGENCY) {
                throw new NotFoundException("Product is not in stock.");
            }

            Order order = new Order();
            order.setCustomer(customer);
            order.setProduct(product);
            order.setOrderDate(LocalDateTime.now());

            var detail = ProductDetailBuilder.of(product).sold(customer);
            product.addProductDetail(detail);

            orders.add(order);
            details.add(detail);
        }

        orderService.saveAll(orders);
        productDetailService.saveAll(details);
    }

    @Override
    public Customer findByEmail(String email) throws NotFoundException {
        var example = new Customer();
        example.setEmail(email);

        var customerWrapper = customerRepository.findOne(Example.of(example));
        if (!customerWrapper.isPresent()) {
            throw new NotFoundException("Customer is not found.");
        }
        return customerWrapper.get();
    }

    @Override
    public Customer findByPhone(String phone) throws NotFoundException {
        var example = new Customer();
        example.setPhoneNumber(phone);

        var customerWrapper = customerRepository.findOne(Example.of(example));
        if (!customerWrapper.isPresent()) {
            throw new NotFoundException("Customer is not found.");
        }
        return customerWrapper.get();
    }

    @Override
    public Customer create(String fullName, String email, String phone) {
        Customer customer = new Customer();
        customer.setFullname(fullName);
        customer.setEmail(email);
        customer.setPhoneNumber(phone);
        
        return customerRepository.save(customer);
    }

}
