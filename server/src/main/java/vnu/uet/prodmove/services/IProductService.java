package vnu.uet.prodmove.services;

import java.util.List;

import vnu.uet.prodmove.entity.Product;

public interface IProductService {
    public List<Product> findProducts(String filter, int pageNumber, String sortBy, String typeSort);
}
