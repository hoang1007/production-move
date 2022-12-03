package vnu.uet.prodmove.utils.querier;

import java.util.ArrayList;

import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.entity.Factory;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.Productdetail;
import vnu.uet.prodmove.entity.WarrantyCenter;
import vnu.uet.prodmove.enums.ProductStage;
import vnu.uet.prodmove.enums.UserRole;

public class ProductDetailQuerier {
    private ArrayList<Productdetail> productDetails;
    
    public ProductDetailQuerier(Product product) {
        this.productDetails = new ArrayList<>(product.getProductDetails());
    }

    public ProductDetailQuerier filter(ProductStage... stages) {
        productDetails.removeIf(productDetail -> {
            for (ProductStage stage : stages) {
                if (productDetail.getStage() == stage) {
                    return true;
                }
            }
            return false;
        });
        return this;
    }

    public ProductDetailQuerier filter(Factory factory) {
        productDetails.removeIf(productDetail -> productDetail.getFactory() != factory);
        return this;
    }

    public ProductDetailQuerier filter(WarrantyCenter warrantyCenter) {
        productDetails.removeIf(productDetail -> productDetail.getWarrantyCenter() != warrantyCenter);
        return this;
    }

    public ProductDetailQuerier filter(Agency agency) {
        productDetails.removeIf(productDetail -> productDetail.getAgency() != agency);
        return this;
    }

    /**
     * 
     * @param id ID của `prop`
     * @param prop Tên của property (vd: `factory`, `warrantyCenter`, `agency`)
     * @return ProductDetailQuerier sau khi lọc
     */
    public ProductDetailQuerier filter(Integer id, UserRole prop) {
        productDetails.removeIf(productDetail -> {
            switch (prop) {
                case FACTORY:
                    if (productDetail.getFactory() == null) {
                        return productDetail.getFactory().getId() != id;
                    } else {
                        return false;
                    }
                case REPAIRER:
                    if (productDetail.getWarrantyCenter() == null) {
                        return productDetail.getWarrantyCenter().getId() != id;
                    } else {
                        return false;
                    }
                case AGENCY:
                    if (productDetail.getAgency() == null) {
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

    public ProductDetailQuerier lastest() {
        var iter = productDetails.iterator();

        if (iter.hasNext()) {
            iter.remove();
        }

        return this;
    }

    public ArrayList<Productdetail> get() {
        return productDetails;
    }

    public Productdetail getFirst() {
        return productDetails.get(0);
    }

    public Productdetail getLast() {
        return productDetails.get(productDetails.size() - 1);
    }

    public Productdetail get(int index) {
        return productDetails.get(index);
    }

    public static ProductDetailQuerier of(Product product) {
        return new ProductDetailQuerier(product);
    }
}
