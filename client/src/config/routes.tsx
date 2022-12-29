import React from 'react';
// import Login from '~/pages/public/Login';

import { SidebarAgency, ContentAgency } from '~/pages/private/Agency'
import { ContentFactory, SidebarFactory } from '~/pages/private/Factory';
import { ContentWarrantyCenter, SidebarWarrantyCenter } from '~/pages/private/WarrantyCenter';
import Login from '~/utils/FakeLogin';

// cái này dùng trong Sidebar ở chỗ Button nhé
export const agencyRoutes = {
    root: '/agency',
    dashboard: '/agency/dashboard',
    import: '/agency/import',
    warranty: '/agency/warranty',
    order: '/agency/order',
    warehouse: '/agency/warehouses',
}

export const factoryRoutes = {
    root: '/factory',
    warehouseList: '/factory/warehouse',
    warehouse: '/factory/warehouse/:id',
    warranty: '/factory/warranty',
    error: '/factory/error',
}

export const warrantyRoutes = {
    root: '/warranty',
    dashboard: '/warranty/dashboard',
    receiveProduct: '/warranty/receive',
    repairing: '/warranty/repairing',
}

export const generalRoutes = {
    product: '/product/:id',
}

const routes: { [key: string]: any } = {
    root: '/',

    public: {
        login: {
            path: '/login',
            element: <Login />
        }
    },

    private: {
        admin: {
            path: '/admin',
            roles: [],
            Sidebar: <></>,
            Content: []
        },

        manufacturingBase: {
            path: factoryRoutes.root,
            roles: ['FACTORY'],
            Sidebar: <SidebarFactory />,
            Content: [
                {
                    title: 'Kho',
                    path: factoryRoutes.warehouseList,
                    element: <ContentFactory.WarehouseList />
                },
                {
                    title: '',
                    path: factoryRoutes.warehouse,
                    element: <ContentFactory.Warehouse />
                },
                {
                    title: 'Sản phẩm lỗi',
                    path: factoryRoutes.warranty,
                    element: <ContentFactory.Warranty />
                },
                {
                    title: '',
                    path: generalRoutes.product,
                    element: <ContentFactory.Product />
                }
            ]
        },

        agency: {
            title: 'AGENCY',
            path: agencyRoutes.root,
            roles: ['AGENCY'],
            Sidebar: <SidebarAgency />,
            Content: [
                {
                    title: 'Dashboard',
                    path: agencyRoutes.dashboard,
                    element: <ContentAgency.Dashboard />
                },
                {
                    title: 'Import new mobile phone',
                    path: agencyRoutes.import,
                    element: <ContentAgency.Import />
                },
                {
                    title: 'Defective & Repaired mobile phone',
                    path: agencyRoutes.warranty,
                    element: <ContentAgency.Warranty />
                },
                {
                    title: 'Orders',
                    path: agencyRoutes.order,
                    element: <ContentAgency.Orders />
                },
                {
                    title: 'All warehouses',
                    path: agencyRoutes.warehouse,
                    element: <ContentAgency.Warehouse />
                },
            ]
        },

        warranty: {
            path: warrantyRoutes.root,
            roles: ['WARRANTY'],
            Sidebar: <SidebarWarrantyCenter />,
            Content: [
                {
                    title: 'Thống kê',
                    path: warrantyRoutes.dashboard,
                    element: <ContentWarrantyCenter.Dashboard/>
                },
                {
                    title: 'Nhận sản phẩm',
                    path: warrantyRoutes.receiveProduct,
                    element: <ContentWarrantyCenter.ReceiveProducts/>
                },
                {
                    title: 'Sửa sản phẩm',
                    path: warrantyRoutes.repairing,
                    element: <ContentWarrantyCenter.RepairProducts/>
                }
            ]
        }

    }
}

export default routes;