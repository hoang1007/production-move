package vnu.uet.prodmove.services;

import java.util.List;

import vnu.uet.prodmove.entity.Product;

public interface IManufactoringBaseService {
    public void importProducts(List<Product> products, Integer warehouseId);

    public void exportToAgency();
}
