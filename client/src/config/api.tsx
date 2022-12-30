const api = {
    login: '/api/login',
    logout: '/api/log-out',

    moderator: {
        root: '/api/moderator',
        allAccounts: '/api/moderator/all_accounts',
        checkPassword: '/api/moderator/check_password',
        updateAccount: '/api/moderator/update_account',
        createAccount: '/api/moderator/create_account',
        deleteAccount: '/api/moderator/delete_account',
    },

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
        receiveNeedRepairs: '/api/agency/receive_need_repairs',
        returnToCustomer: '/api/agency/return_to_customer',
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
        returnToAgency: '/api/warranty_center/return_to_agency',
        returnToFactory: '/api/warranty_center/return_to_factory',
    },
    customer: {
        root: '/api/customer',
        getByEmail: '/api/customer/get_by_email/',
        getByPhone: '/api/customer/get_by_phone/',
        create: '/api/customer/create',
        order: '/api/customer/orders',
    }
}

export default api;