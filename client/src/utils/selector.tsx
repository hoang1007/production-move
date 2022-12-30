export const ProductStage = {
    // NEW_PRODUCTION: 'NEW PRODUCTION',
    // EXPORT_TO_AGENCY: 'EXPORT TO AGENCY',
    // SOLD: 'SOLD',
    // NEED_REPAIR: 'NEED REPAIR',
    // REPAIRING: 'REPAIRING',
    // REPAIRED: 'REPAIRED',
    // RETURNED_TO_CUSTOMER: 'RETURNED TO CUSTOMER',
    // NEED_RETURN_TO_FACTORY: 'NEED RETURN TO FACTORY',
    // RETURNED_TO_FACTORY: 'RETURNED TO FACTORY',
    // NEED_RECALL: 'NEED RECALL',
    // WARRANTY_EXPIRED: 'WARRANTY EXPIRED',
    // CANNOT_SOLD: 'CANNOT SOLD'
    NEW_PRODUCTION: 'New production',
    EXPORT_TO_AGENCY: 'Export to agency',
    SOLD: 'Sold',
    NEED_REPAIR: 'Need repair',
    REPAIRING: 'Repairing',
    REPAIRED: 'Repaired',
    RETURNED_TO_CUSTOMER: 'Returned to customer',
    NEED_RETURN_TO_FACTORY: 'Need return to factory',
    RETURNED_TO_FACTORY: 'Returned to factory',
    NEED_RECALL: 'Need recall',
    WARRANTY_EXPIRED: 'Warranty expired',
    CANNOT_SOLD: 'Cannot sold'
}

export const groupBy = function<T>(items: T[], keyExtractor: (item: T) => any, keyLabelGetter?: (key: any) => any) {
    var group: Record<any, T[]> = {};

    items.forEach(product => {
        let key = keyExtractor(product);

        if (group[key]) {
            group[key].push(product);
        } else {
            group[key] = [product];
        }
    });

    var ret = Object.entries(group).map(([key, value]) => {
        return {
            name: keyLabelGetter ? keyLabelGetter(key) : key,
            value: value
        }
    });

    return ret;
}