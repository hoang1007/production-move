package vnu.uet.prodmove.services;

import vnu.uet.prodmove.entity.WarrantyCenter;
import vnu.uet.prodmove.exception.NotFoundException;

public interface IWarrantyCenterService {

    WarrantyCenter findById(Integer id) throws NotFoundException;

    /**
     * Nhận sản phẩm cần bảo hành hoặc bị triệu hồi từ đại lý phân phối
     * @param productIds Danh sách mã sản phẩm
     */
    public void receiveProductsFromAgency(Iterable<Integer> productIds);

    /**
     * Chuyển sản phẩm đã sửa chữa xong tới đại lý phân phối
     * @param productIds Danh sách mã sản phẩm
     */
    public void returnProductsToAgency(Iterable<Integer> productIds);

    /**
     * Chuyển sản phẩm không thể sửa chữa cho cơ sở sản xuất
     * @param productIds Danh sách mã sản phẩm
     * @param factoryId Mã cơ sở sản xuất
     */
    public void returnProductsToFactory(Iterable<Integer> productIds, Integer factoryId);
}
