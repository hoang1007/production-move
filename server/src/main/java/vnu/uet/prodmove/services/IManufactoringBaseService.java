package vnu.uet.prodmove.services;

import java.util.Collection;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.ProductDetail;
import vnu.uet.prodmove.entity.Warehouse;

public interface IManufactoringBaseService {
    /**
     * Chuyển sản phẩm mới sản xuất tới kho của cơ sở sản xuất
     * @param productlineId Mã dòng sản phẩm
     * @param quantity Số lượng sản phẩm mới
     * @param warehouseId Mã kho để nhập sản phẩm
     */
    public void importProducts(Integer productlineId, Integer quantity, Integer warehouseId);

    /**
     * Chuyển sản phẩm đến đại lý phân phối
     * @param productIds Danh sách mã sản phẩm
     * @param agencyId Mã đại lý phân phối
     */
    public void exportToAgency(Iterable<Integer> productIds, Integer agencyId);

    /**
     * Nhận lại sản phẩm từ trung tâm bảo hành
     * @param productIds Danh sách mã sản phẩm
     */
    public void receiveReturnedProducts(Iterable<Integer> productIds);

    /**
     * Lấy tất cả các kho của cơ sở sản xuất
     * @return
     */
    public Collection<Warehouse> getAllWarehouses(Integer factoryId);

    /**
     * Lấy tất cả các sản phẩm được sản xuất tại cơ sở sản xuất
     * @param factoryId Mã cơ sở sản xuất
     * @return
     */
    public Collection<Product> getAllCreatedProducts(Integer factoryId);
}