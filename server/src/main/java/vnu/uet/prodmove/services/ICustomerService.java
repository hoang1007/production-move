package vnu.uet.prodmove.services;

import java.util.Collection;

import vnu.uet.prodmove.entity.Customer;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.exception.NotFoundException;

public interface ICustomerService {
    Customer findById(Integer id) throws NotFoundException;

    Customer findByEmail(String email) throws NotFoundException;

    Customer findByPhone(String phone) throws NotFoundException;

    Customer create(String fullName, String email, String phone);

    void buyProducts(Collection<Product> products, Customer customer) throws CloneNotSupportedException;

    void orderProducts(Collection<Integer> productIds, Integer customerId) throws NotFoundException;
}
