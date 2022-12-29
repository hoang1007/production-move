package vnu.uet.prodmove.services;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.utils.dataModel.WarehouseModel;

public interface IAgencyService {

    Agency findById(Integer id) throws NotFoundException;

    Warehouse createWarehouse(Integer agencyId, WarehouseModel warehouseModel) throws Exception;

    /**
     * Get all agencies
     * @return
     */
    List<Agency> findAll();

    /**
     * Import pending products into a warehouse of agency.
     * 
     * @param agencyId    ID of the agency
     * @param warehouseId ID of the warehouse
     * @param productIds  list ID of the pending products
     * @throws NotFoundException
     * @throws CloneNotSupportedException
     */
    void importPendingProductsFromFactory(Integer agencyId, Integer warehouseId, Collection<String> productIds)
                    throws NotFoundException, CloneNotSupportedException;
    

    /**
     * Get all warehouses of specific agency
     * @param agencyId ID of agency
     * @return Collection of warehouses
     * @throws NotFoundException
     */
    Collection<Warehouse> getAllWarehouses(Integer agencyId) throws NotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
    
    /**
     * Sell products to customer.
     * @param customerId ID of the customer
     * @param productIds Collection of product IDs.
     * @throws CloneNotSupportedException
     */
    void sellProducts(Integer customerId, Collection<Integer> productIds) throws NotFoundException, CloneNotSupportedException;

    public Collection<Order> getAllOrders(Integer agencyId) throws NotFoundException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException;

    /**
     * Nhận các sản phẩm cần bảo hành từ khách hàng và lưu vào kho để chờ gửi xuống trung tâm bảo hành.
     * @param productIds Danh sách mã sản phẩm
     */
    public void receiveNeedRepairProducts(Iterable<Integer> productIds, Integer warehouseId) throws NotFoundException;

    /**
     * Chuyển các sản phẩm cần bảo hành đến trung tâm bảo hành
     * @param productIds Danh sách mã sản phẩm
     * @param warrantyCenterId Mã trung tâm bảo hành
     */
    public void transferProductToWarrantyCenter(Iterable<Integer> productIds, Integer warrantyCenterId) 
            throws NotFoundException;

    /**
     * Nhận các sản phẩm đã được bảo hành từ trung tâm bảo hành
     * @param productIds Danh sách mã sản phẩm
     * @param warrantyCenterId Mã trung tâm bảo hành
     */
    public void receiveProductsFromWarrantyCenter(Iterable<Integer> productIds, Integer warrantyCenterId);

    /**
     * Trả sản phẩm đã được bảo hành cho khách hàng
     * @param productId Mã sản phẩm
     * @param customerId Mã khách hàng
     */
    public void returnToCustomer(Integer productId, Integer customerId);

    /**
     * Triệu hồi các sản phẩm của dòng sản phẩm bị lỗi
     * @param productlineId Mã dòng sản phẩm
     */
    public void recallProducts(Integer productlineId);

    /**
     * Trả các sản phẩm không sửa chữa được cho cơ sở sản xuất
     * @param productIds Danh sách mã sản phẩm
     * @param factoryId Mã cơ sở sản xuất
     */
    public void returnProductsToFactory(Iterable<Integer> productIds, Integer factoryId);

    public Collection<Product> getPendingProducts(Integer agencyId) throws NotFoundException;
}
