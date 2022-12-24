package vnu.uet.prodmove.utils.builder;

import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.entity.Customer;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.ProductDetail;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.entity.WarrantyCenter;
import vnu.uet.prodmove.enums.ProductStage;
import vnu.uet.prodmove.utils.querier.ProductDetailQuerier;

/**
 * Hỗ trợ khởi tạo {@link Productdetail} theo các trạng thái của sản phẩm.
 * <p>
 * Tự động cập nhật các trường thông tin của {@link Productdetail} theo trạng thái của sản phẩm. VD: completed...
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

    public ProductDetail waitToRepair() {
        detail = ProductDetailQuerier.of(this.detail.getProduct()).getLast();
        if (detail.getStage() == ProductStage.SOLD) {
            detail.setStage(ProductStage.NEED_REPAIR);
            return detail;
        }
        throw new IllegalArgumentException("Product is not sold yet.");
    }

    public ProductDetail repairing() {
        var needRepair = ProductDetailQuerier.of(this.detail.getProduct()).getLast();

        if (needRepair.getStage() == ProductStage.NEED_REPAIR) {
            needRepair.markCompleted();

            detail.setWarrantyCenter(needRepair.getWarrantyCenter());
            detail.setStage(ProductStage.REPAIRING);
            return detail;
        } else {
            throw new IllegalArgumentException("Product is not in need repair stage");
        }
    }

    public ProductDetail repaired() {
        var querier = new ProductDetailQuerier(this.detail.getProduct());
        querier.filter(ProductStage.NEED_REPAIR, ProductStage.REPAIRING);

        var repairing = querier.getLast();
    
        if (repairing.getStage() == ProductStage.REPAIRING) {
            repairing.markCompleted();

            var needRepair = querier.filter(ProductStage.NEED_REPAIR).getLast();

            detail.setAgency(needRepair.getAgency());

            return detail;
        } else {
            throw new IllegalArgumentException("Product is not in repairing stage");
        }
    }
}
