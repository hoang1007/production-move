package vnu.uet.prodmove.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductStage {
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
    CANNOT_SOLD("Cannot sold"),
    /** Bồi thường */
    COMPENSATE("Compensate");

    private final String type;

    private ProductStage(String type) {
        this.type = type;
    }

    @Override
    @JsonValue
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
