package vnu.uet.prodmove.services;

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
}