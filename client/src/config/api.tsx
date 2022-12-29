const api = {
    login: '/api/login',
    logout: '/api/log-out',

    agency: {
        root: '/api/agency',
        allWarehouses: '/api/agency/warehouses',
        importProducts: '/api/agency/import_products',
        pendingProducts: '/api/agency/pending_products',
        getOrders: '/api/agency/orders',
        sellProduct: '/api/agency/sell_product',
    },
    factory: {
        warehouseList: '/factory/warehouse',
        warranty: '/factory/warranty',
        error: '/factory/error',
    }
}

export default api;