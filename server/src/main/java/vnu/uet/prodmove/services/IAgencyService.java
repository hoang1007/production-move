package vnu.uet.prodmove.services;

public interface IAgencyService {
    /**
     * Nhận sản phẩm mới từ cơ sở sản xuất
     * @param productIds Danh sách mã sản phẩm
     * @param factoryId Mã cơ sở sản xuất
     * @param warehouseId Mã kho nhận của đại lý
     */
    public void importProductsFromFactory(Iterable<Integer> productIds, Integer factoryId, Integer warehouseId);

    /**
     * Bán sản phẩm cho khách hàng
     * @param productId Mã sản phẩm
     * @param customerId Mã khách hàng
     */
    public void sellProduct(Integer productId, Integer customerId);

    /**
     * Nhận các sản phẩm cần bảo hành từ khách hàng
     * @param productIds Danh sách mã sản phẩm
     */
    public void receiveNeedRepairProducts(Iterable<Integer> productIds);

    /**
     * Chuyển các sản phẩm cần bảo hành đến trung tâm bảo hành
     * @param productIds Danh sách mã sản phẩm
     * @param warrantyCenterId Mã trung tâm bảo hành
     */
    public void transferProductToWarrantyCenter(Iterable<Integer> productIds, Integer warrantyCenterId);

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
}
