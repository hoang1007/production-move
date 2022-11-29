package vnu.uet.prodmove.utils.builder;

import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.entity.Customer;
import vnu.uet.prodmove.entity.Productdetail;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.enums.ProductStage;

/**
 * Hỗ trợ khởi tạo {@link Productdetail} theo các trạng thái của sản phẩm
 */
public class ProductDetailBuilder {
    public static Productdetail newProduction(Warehouse warehouse) {
        if (warehouse.isFactory()) {
            Productdetail detail = new Productdetail();
            detail.setWarehouse(warehouse);
            detail.setStage(ProductStage.NEW_PRODUCTION);
            detail.markCompleted();
            return detail;
        } else {
            throw new IllegalArgumentException("Warehouse is not belong to a factory");
        }
    }

    public static Productdetail exportToAgency(Agency agency) {
        Productdetail detail = new Productdetail();
        detail.setAgency(agency);
        detail.setStage(ProductStage.EXPORT_TO_AGENCY);
        return detail;
    }

    public static Productdetail sold(Customer customer) {
        Productdetail detail = new Productdetail();
        detail.setCustomer(customer);
        detail.setStage(ProductStage.SOLD);
        return detail;
    }
}
