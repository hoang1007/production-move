package vnu.uet.prodmove.services;

import java.util.Collection;

import vnu.uet.prodmove.entity.Customer;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.exception.NotFoundException;

public interface ICustomerService {
    Customer findById(Integer id) throws NotFoundException;

    void buyProducts(Collection<Product> products, Customer customer) throws CloneNotSupportedException;
}
