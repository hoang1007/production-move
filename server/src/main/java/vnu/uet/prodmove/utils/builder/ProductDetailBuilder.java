package vnu.uet.prodmove.utils.builder;

import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.entity.Customer;
import vnu.uet.prodmove.entity.Factory;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.ProductDetail;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.entity.WarrantyCenter;
import vnu.uet.prodmove.enums.ProductStage;

/**
 * Hỗ trợ khởi tạo {@link Productdetail} theo các trạng thái của sản phẩm.
 */
public class ProductDetailBuilder {
    private ProductDetail detail;

    public ProductDetailBuilder(ProductDetail productDetail) {
        this.detail = productDetail;
    }

    public static ProductDetailBuilder of(Product product) {
        ProductDetail detail = new ProductDetail();
        detail.setProduct(product);
        return new ProductDetailBuilder(detail);
    }

    public ProductDetail newProduction(Warehouse warehouse) {
        if (warehouse.isFactory()) {
            detail.setWarehouse(warehouse);
            detail.setStage(ProductStage.NEW_PRODUCTION);
            detail.markCompleted();
            return detail;
        } else {
            throw new IllegalArgumentException("Warehouse is not belong to a factory");
        }
    }

    public ProductDetail exportToAgency(Agency agency) {
        detail.setAgency(agency);
        detail.setStage(ProductStage.EXPORT_TO_AGENCY);
        return detail;
    }

    public ProductDetail sold(Customer customer) {
        detail.setCustomer(customer);
        detail.setStage(ProductStage.SOLD);
        return detail;
    }

    public ProductDetail repairing(WarrantyCenter warrantyCenter) {
        detail.setWarrantyCenter(warrantyCenter);
        detail.setStage(ProductStage.REPAIRING);
        return detail;
    }

    public ProductDetail repaired(WarrantyCenter warrantyCenter, Agency agency) {
        detail.setAgency(agency);
        detail.setWarrantyCenter(warrantyCenter);
        return detail;
    }

    public ProductDetail needReturnToFactory(WarrantyCenter warrantyCenter) {
        detail.setWarrantyCenter(warrantyCenter);
        detail.setStage(ProductStage.NEED_RETURN_TO_FACTORY);
        return detail;
    }

    public ProductDetail returnedToFactory(Factory factory) {
        detail.setFactory(factory);
        detail.setStage(ProductStage.RETURNED_TO_FACTORY);
        return detail;
    }
}
