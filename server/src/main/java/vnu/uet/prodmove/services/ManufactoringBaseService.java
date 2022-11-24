package vnu.uet.prodmove.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.repos.ProductRepository;

@Service
public class ManufactoringBaseService {
    @Autowired
    private ProductRepository productRepository;

    public void importProducts(List<Product> products, Integer warehouseId) {
        
    }

    public void exportToAgency() {

    }
}