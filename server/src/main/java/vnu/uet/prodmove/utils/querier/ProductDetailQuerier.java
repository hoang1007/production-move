package vnu.uet.prodmove.utils.querier;

import java.util.ArrayList;

import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.entity.Factory;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.ProductDetail;
import vnu.uet.prodmove.entity.WarrantyCenter;
import vnu.uet.prodmove.enums.ProductStage;
import vnu.uet.prodmove.enums.UserRole;

public class ProductDetailQuerier {
    private ArrayList<ProductDetail> productDetails;

    public ProductDetailQuerier(Product product) {
        this.productDetails = new ArrayList<>(product.getProductDetails());
    }

    public ProductDetailQuerier filter(ProductStage... stages) {
        productDetails.removeIf(productDetail -> {
            for (ProductStage stage : stages) {
                if (productDetail.getStage() == stage) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }

    public ProductDetailQuerier filter(Factory factory) {
        productDetails.removeIf(productDetail -> productDetail.getFactory().getId() != factory.getId());
        return this;
    }

    public ProductDetailQuerier filter(WarrantyCenter warrantyCenter) {
        productDetails.removeIf(productDetail -> productDetail.getWarrantyCenter().getId() != warrantyCenter.getId());
        return this;
    }

    public ProductDetailQuerier filter(Agency agency) {
        productDetails.removeIf(productDetail -> productDetail.getAgency().getId() != agency.getId());
        return this;
    }

    /**
     * 
     * @param id   ID của `prop`
     * @param prop Tên của property (vd: `factory`, `warrantyCenter`, `agency`)
     * @return ProductDetailQuerier sau khi lọc
     */
    public ProductDetailQuerier filter(Integer id, UserRole prop) {
        productDetails.removeIf(productDetail -> {
            switch (prop) {
                case FACTORY:
                    if (productDetail.getFactory() != null) {
                        return productDetail.getFactory().getId() != id;
                    } else {
                        return false;
                    }
                case REPAIRER:
                    if (productDetail.getWarrantyCenter() != null) {
                        return productDetail.getWarrantyCenter().getId() != id;
                    } else {
                        return false;
                    }
                case AGENCY:
                    if (productDetail.getAgency() != null) {
                        return productDetail.getAgency().getId() != id;
                    } else {
                        return false;
                    }
                default:
                    return false;
            }
        });
        return this;
    }

    public ProductDetailQuerier pending() {
        productDetails.removeIf(productDetail -> productDetail.completed());
        return this;
    }

    public ProductDetailQuerier completed() {
        productDetails.removeIf(productDetail -> !productDetail.completed());
        return this;
    }

    public ProductDetailQuerier latest() {
        var iter = productDetails.iterator();

        while (iter.hasNext()) {
            iter.remove();
        }

        return this;
    }

    public ArrayList<ProductDetail> get() {
        return productDetails;
    }

    public ProductDetail getFirst() {
        return productDetails.get(0);
    }

    public ProductDetail getLast() {
        return productDetails.get(productDetails.size() - 1);
    }

    public ProductDetail get(int index) {
        return productDetails.get(index);
    }

    public static ProductDetailQuerier of(Product product) {
        return new ProductDetailQuerier(product);
    }
}
