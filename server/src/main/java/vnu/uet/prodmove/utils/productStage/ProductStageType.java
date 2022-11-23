package vnu.uet.prodmove.utils.productStage;

public enum ProductStageType {
    /** Mới sản xuất */
    NEW_PRODUCTION("New production"),
    /** Chuyển về đại lý */
    EXPORT_TO_AGENCY("Export to agency"),
    /** Đã bán */
    SOLD("Sold"),
    /** Lỗi, cần bảo hành */
    NEED_REPAIR("Need repair"),
    /** Đang sửa chữa, bảo hành */
    REPAIRING("Repairing"),
    /** Đã bảo hành xong, quay lại đại lý */
    REPAIRED("Repaired"),
    /** Đã trả lại bảo hành cho khách hàng */
    RETURNED_TO_CUSTOMER("Returned to customer"),
    /** Lỗi, cần trả về nhà máy*/
    NEED_RETURN_TO_FACTORY("Need return to factory"),
    /** Lỗi, đã đưa về cơ sở sản xuất */
    RETURNED_TO_FACTORY("Returned to factory"),
    /** Lỗi, cần triệu hồi */
    NEED_RECALL("Need recall"),
    /** Hết thời gian bảo hành */
    WARRANTY_EXPIRED("Warranty expired"),
    /** Trả lại cơ sở sản xuất (do lâu không bán được) */
    CANNOT_SOLD("Cannot sold");

    private final String type;

    private ProductStageType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public static ProductStageType fromString(String type) {
        for (ProductStageType pType : ProductStageType.values()) {
            if (pType.type.equalsIgnoreCase(type)) {
                return pType;
            }
        }

        return null;
    }
}
