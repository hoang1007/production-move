package vnu.uet.prodmove.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.repos.ProductRepository;
import vnu.uet.prodmove.services.IManufactoringBaseService;

@Service
public class ManufactoringBaseService implements IManufactoringBaseService {
    @Autowired
    private ProductRepository productRepository;

    public void importProducts(List<Product> products, Integer warehouseId) {
        
    }

    public void exportToAgency() {

    }
}