import { WarehouseType } from './TypeGlobal';

const ProductStage = {
    NEW_PRODUCTION: 'NEW_PRODUCTION',
    EXPORT_TO_AGENCY: 'EXPORT_TO_AGENCY',
    SOLD: 'SOLD',
    NEED_REPAIR: 'NEED_REPAIR',
    REPAIRING: 'REPAIRING',
    REPAIRED: 'REPAIRED',
    RETURNED_TO_CUSTOMER: 'RETURNED_TO_CUSTOMER',
    NEED_RETURN_TO_FACTORY: 'NEED_RETURN_TO_FACTORY',
    RETURNED_TO_FACTORY: 'RETURNED_TO_FACTORY',
    NEED_RECALL: 'NEED_RECALL',
    WARRANTY_EXPIRED: 'WARRANTY_EXPIRED',
    CANNOT_SOLD: 'CANNOT_SOLD'
}

// agency
export const importedProductsSelector = (warehouse: WarehouseType) => {
    const productdetails = warehouse.productdetails.filter(pd => pd.stage?.toLowerCase() === ProductStage.EXPORT_TO_AGENCY.toLowerCase() && (pd.end_at !== null));
    return {
        ...warehouse,
        productdetails
    };
}

export const pendingProductsSelector = (warehouse: WarehouseType) => {
    console.log(warehouse)
    const productdetails = warehouse.productdetails.filter(pd => pd.stage?.toLowerCase() === ProductStage.EXPORT_TO_AGENCY.toLowerCase() && (pd.end_at === null));
    console.log({
        ...warehouse,
        productdetails
    })
    return {
        ...warehouse,
        productdetails
    };
}