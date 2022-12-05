package vnu.uet.prodmove.services;

public interface IWarrantyCenterService {
    /**
     * Nhận sản phẩm cần bảo hành hoặc bị triệu hồi từ đại lý phân phối
     * @param productIds Danh sách mã sản phẩm
     */
    public void receiveProductsFromAgency(Iterable<Integer> productIds);

    /**
     * Chuyển sản phẩm đã sửa chữa xong tới đại lý phân phối
     * @param productIds Danh sách mã sản phẩm
     * @param agencyId Mã đại lý phân phối
     */
    public void returnProductsToAgency(Iterable<Integer> productIds);

    /**
     * Chuyển sản phẩm không thể sửa chữa cho cơ sở sản xuất
     * @param productIds Danh sách mã sản phẩm
     * @param factoryId Mã cơ sở sản xuất
     */
    public void returnProductsToFactory(Iterable<Integer> productIds, Integer factoryId);

    /**
     * Thông báo tới các bên liên quan về sản phẩm không thể sửa chữa
     * @param productIds Danh sách mã sản phẩm
     */
    public void annouceCannotRepairProducts(Iterable<Integer> productIds);
}
