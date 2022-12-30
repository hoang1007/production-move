package vnu.uet.prodmove.services;

import java.util.List;

import vnu.uet.prodmove.entity.ProductDetail;
import vnu.uet.prodmove.entity.WarrantyCenter;
import vnu.uet.prodmove.exception.NotFoundException;

public interface IWarrantyCenterService {

    WarrantyCenter findById(Integer id) throws NotFoundException;

    WarrantyCenter create(String name, String address);

    void delete(Integer id);

    public List<WarrantyCenter> findAll();

    /**
     * Lấy danh sách sản phẩm đang sửa chữa
     * 
     * @param warrantyCenterId Mã cơ sở bảo hành
     * @return
     */
    public List<ProductDetail> getRepairingProducts(Integer warrantyCenterId); 

    /**
     * Lấy danh sách sản phẩm cần sửa chữa
     * 
     * @return
     */
    public List<ProductDetail> getNeedRepairProducts();

    /**
     * Nhận sản phẩm cần bảo hành hoặc bị triệu hồi từ đại lý phân phối
     * 
     * @param productIds Danh sách mã sản phẩm
     */
    public void receiveProductsFromAgency(Iterable<Integer> productIds, Integer warrantyCenterId);

    /**
     * Chuyển sản phẩm đã sửa chữa xong tới đại lý phân phối
     * 
     * @param productIds Danh sách mã sản phẩm
     */
    public void returnProductsToAgency(Iterable<Integer> productIds);

    /**
     * Chuyển sản phẩm không thể sửa chữa cho cơ sở sản xuất
     * 
     * @param productIds Danh sách mã sản phẩm
     */
    public void returnProductsToFactory(Iterable<Integer> productIds);

    /**
     * Thông báo tới các bên liên quan về sản phẩm không thể sửa chữa
     * 
     * @param productIds Danh sách mã sản phẩm
     */
    public void annouceCannotRepairProducts(Iterable<Integer> productIds);
}
