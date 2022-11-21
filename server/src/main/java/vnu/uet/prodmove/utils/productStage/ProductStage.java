package vnu.uet.prodmove.utils.productStage;

public enum ProductStage {
    /** Mới sản xuất */
    NEW_PRODUCTION,
    /** Chuyển về đại lý */
    TRANSFER_TO_AGENCY,
    /** Đã bán */
    SOLD,
    /** Lỗi, cần bảo hành */
    NEED_REPAIR,
    /** Đang sửa chữa, bảo hành */
    REPAIRING,
    /** Đã bảo hành xong, quay lại đại lý */
    REPAIRED,
    /** Đã trả lại bảo hành cho khách hàng */
    RETURNED_TO_CUSTOMER,
    /** Lỗi, cần trả về nhà máy*/
    NEED_RETURN_TO_FACTORY,
    /** Lỗi, đã đưa về cơ sở sản xuất */
    RETURNED_TO_FACTORY,
    /** Lỗi, cần triệu hồi */
    NEED_RECALL,
    /** Hết thời gian bảo hành */
    WARRANTY_EXPIRED,
    /** Trả lại cơ sở sản xuất (do lâu không bán được) */
    CANNOT_SOLD
}
