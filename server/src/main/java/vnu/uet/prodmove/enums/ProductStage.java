package vnu.uet.prodmove.enums;

public enum ProductStage {
    /** Mới sản xuất */
    NEW_PRODUCTION("New_production"),
    /** Chuyển về đại lý */
    EXPORT_TO_AGENCY("Export_to_agency"),
    /** Đã bán */
    SOLD("Sold"),
    /** Lỗi, cần bảo hành */
    NEED_REPAIR("Need_repair"),
    /** Đang sửa chữa, bảo hành */
    REPAIRING("Repairing"),
    /** Đã bảo hành xong, quay lại đại lý */
    REPAIRED("Repaired"),
    /** Đã trả lại bảo hành cho khách hàng */
    RETURNED_TO_CUSTOMER("Returned_to_customer"),
    /** Lỗi, cần trả về nhà máy*/
    NEED_RETURN_TO_FACTORY("Need_return_to_factory"),
    /** Lỗi, đã đưa về cơ sở sản xuất */
    RETURNED_TO_FACTORY("Returned_to_factory"),
    /** Lỗi, cần triệu hồi */
    NEED_RECALL("Need_recall"),
    /** Hết thời gian bảo hành */
    WARRANTY_EXPIRED("Warranty_expired"),
    /** Trả lại cơ sở sản xuất (do lâu không bán được) */
    CANNOT_SOLD("Cannot_sold");

    private final String type;

    private ProductStage(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public static ProductStage fromString(String type) {
        for (ProductStage pType : ProductStage.values()) {
            if (pType.type.equalsIgnoreCase(type)) {
                return pType;
            }
        }

        return null;
    }
}
