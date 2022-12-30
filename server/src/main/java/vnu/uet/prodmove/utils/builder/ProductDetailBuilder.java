package vnu.uet.prodmove.utils.builder;

import java.util.List;

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
 * Tự động cập nhật các trường thông tin của {@link Productdetail} theo trạng
 * thái của sản phẩm. VD: completed...
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
            detail.setFactory(warehouse.getFactory());
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

    public ProductDetail needRepair(String reason) {
        var lastDetail = ProductDetailQuerier.of(this.detail.getProduct()).getLast();
        if (lastDetail.getStage().equals(ProductStage.SOLD)) {
            detail.setStage(ProductStage.NEED_REPAIR);
            detail.setDescription(reason);
            return detail;
        }
        throw new IllegalArgumentException("Product is not sold yet.");
    }

    public List<ProductDetail> repairing(WarrantyCenter warrantyCenter) {
        var needRepair = ProductDetailQuerier.of(this.detail.getProduct()).getLast();

        if (needRepair.getStage().equals(ProductStage.NEED_REPAIR)) {
            needRepair.markCompleted();

            detail.setWarrantyCenter(warrantyCenter);
            detail.setDescription(needRepair.getDescription());
            detail.setStage(ProductStage.REPAIRING);
            return List.of(detail, needRepair);
        } else {
            throw new IllegalArgumentException("Product is not in need repair stage");
        }
    }

    public List<ProductDetail> repaired() {
        var querier = new ProductDetailQuerier(this.detail.getProduct());

        var repairing = querier.getLast();

        if (repairing.getStage() == ProductStage.REPAIRING) {
            repairing.markCompleted();

            var agency = querier.filter(ProductStage.EXPORT_TO_AGENCY).getLast().getAgency();
            detail.setWarrantyCenter(repairing.getWarrantyCenter());
            detail.setAgency(agency);
            detail.setDescription(repairing.getDescription());
            detail.setStage(ProductStage.REPAIRED);

            return List.of(detail, repairing);
        } else {
            throw new IllegalArgumentException("Product is not in repairing stage");
        }
    }

    public List<ProductDetail> needReturnToFactory() {
        var querier = new ProductDetailQuerier(this.detail.getProduct());

        var repairing = querier.getLast();

        if (repairing.getStage() == ProductStage.REPAIRING) {
            repairing.markCompleted();

            var factory = querier.filter(ProductStage.NEW_PRODUCTION).getLast().getFactory();

            detail.setFactory(factory);
            detail.setDescription(repairing.getDescription());
            detail.setWarrantyCenter(repairing.getWarrantyCenter());
            detail.setStage(ProductStage.NEED_RETURN_TO_FACTORY);
            return List.of(detail, repairing);
        } else {
            throw new IllegalArgumentException("Product is not in repairing stage");
        }
    }

    public List<ProductDetail> returnedToFactory() {
        var querier = new ProductDetailQuerier(this.detail.getProduct());

        var needReturnToFactory = querier.getLast();

        if (needReturnToFactory.getStage() == ProductStage.NEED_RETURN_TO_FACTORY) {
            needReturnToFactory.markCompleted();

            detail.setFactory(needReturnToFactory.getFactory());
            detail.setStage(ProductStage.RETURNED_TO_FACTORY);
            detail.markCompleted();
            return List.of(detail, needReturnToFactory);
        } else {
            throw new IllegalArgumentException("Product is not in need return to factory stage");
        }
    }

    public List<ProductDetail> returnedToCustomer() {
        var querier = new ProductDetailQuerier(this.detail.getProduct());

        var repaired = querier.getLast();

        if (repaired.getStage() == ProductStage.REPAIRED) {
            repaired.markCompleted();

            var customer = querier.filter(ProductStage.SOLD).getLast().getCustomer();

            detail.setCustomer(customer);
            detail.setFactory(repaired.getFactory());
            detail.setStage(ProductStage.RETURNED_TO_CUSTOMER);
            detail.markCompleted();
            return List.of(detail, repaired);
        } else {
            throw new IllegalArgumentException("Product is not in repaired stage");
        }
    }
}
