const api = {
    login: '/api/login',
    logout: '/api/log-out',
    productline: {
        productlineInfo: '/api/productline/',
        productlines: '/api/productline/all',
    },
    agency: {
        root: '/api/agency',
        allWarehouses: '/api/agency/warehouses',
        importProducts: '/api/agency/import_products',
        pendingProducts: '/api/agency/pending_products',
        getOrders: '/api/agency/orders',
        sellProduct: '/api/agency/sell_product',
        allAgencies: '/api/agency/all',
        distributedProducts: '/api/agency/distributed_products',
    },
    factory: {
        root: '/api/factory',
        allWarehouses: '/api/factory/warehouses',
        importProducts: '/api/factory/import',
        exportProducts: '/api/factory/export',
        allCreatedProducts: '/api/factory/all_created',
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