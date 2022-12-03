package vnu.uet.prodmove.utils.builder;

import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.entity.Customer;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.Productdetail;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.enums.ProductStage;
import vnu.uet.prodmove.utils.querier.ProductDetailQuerier;

/**
 * Hỗ trợ khởi tạo {@link Productdetail} theo các trạng thái của sản phẩm.
 * <p>
 * Tự động cập nhật các trường thông tin của {@link Productdetail} theo trạng thái của sản phẩm. VD: completed...
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

    public static Productdetail repairing(Product product) {
        Productdetail detail = new Productdetail();
        var needRepair = ProductDetailQuerier.of(product).getLast();

        if (needRepair.getStage() == ProductStage.NEED_REPAIR) {
            needRepair.markCompleted();

            detail.setWarrantyCenter(needRepair.getWarrantyCenter());
            detail.setStage(ProductStage.REPAIRING);
            return detail;
        } else {
            throw new IllegalArgumentException("Product is not in need repair stage");
        }
    }

    public static Productdetail repaired(Product product) {
        Productdetail detail = new Productdetail();
        var querier = new ProductDetailQuerier(product);
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
