package vnu.uet.prodmove.utils.builder;

import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.entity.Customer;
import vnu.uet.prodmove.entity.ProductDetail;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.enums.ProductStage;

/**
 * Hỗ trợ khởi tạo {@link ProductDetail} theo các trạng thái của sản phẩm
 */
public class ProductDetailBuilder {
    public static ProductDetail newProduction(Warehouse warehouse) {
        if (warehouse.isFactory()) {
            ProductDetail detail = new ProductDetail();
            detail.setWarehouse(warehouse);
            detail.setStage(ProductStage.NEW_PRODUCTION);
            detail.markCompleted();
            return detail;
        } else {
            throw new IllegalArgumentException("Warehouse is not belong to a factory");
        }
    }

    public static ProductDetail exportToAgency(Agency agency) {
        ProductDetail detail = new ProductDetail();
        detail.setAgency(agency);
        detail.setStage(ProductStage.EXPORT_TO_AGENCY);
        return detail;
    }

    public static ProductDetail sold(Customer customer) {
        ProductDetail detail = new ProductDetail();
        detail.setCustomer(customer);
        detail.setStage(ProductStage.SOLD);
        return detail;
    }
}
