const api = {
    productline: {
        productlineInfo: '/api/productline/',
        productlines: '/api/productline/all',
    },
    agency: {
        root: '/api/agency',
        allWarehouses: '/api/agency/warehouses',
        importProducts: '/api/agency/import_products',
        getOrders: '/api/agency/orders',
        sellProduct: '/api/agency/sell_product',
        allAgencies: '/api/agency/all',
    },
    factory: {
        root: '/api/factory',
        allWarehouses: '/api/factory/warehouses',
        importProducts: '/api/factory/import',
        exportProducts: '/api/factory/export',
    },
    warehouse: {
        root: '/api/warehouse',
        newProducts: '/api/warehouse/new_products',
    },
    warrantyCenter: {
        root: '/api/warranty_center',
        needRepairProducts: '/api/warranty_center/need_repair',
        receiveProducts: '/api/warranty_center/receive',
        repairingProducts: '/api/warranty_center/repairing',
    }
}

export default api;