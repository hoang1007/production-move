package vnu.uet.prodmove.services;

import java.util.Collection;

import vnu.uet.prodmove.entity.Product;

public interface IProductdetailService {
    void saveProducts(Iterable<Product> products);
}
