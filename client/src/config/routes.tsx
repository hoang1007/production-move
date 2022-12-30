import Login from '~/pages/public/Login';

import { SidebarAgency, ContentAgency } from '~/pages/private/Agency'
import { ContentFactory, SidebarFactory } from '~/pages/private/Factory';
import { ContentWarrantyCenter, SidebarWarrantyCenter } from '~/pages/private/WarrantyCenter';
import { ContentAdmin, SidebarAdmin } from '~/pages/private/Admin';
import NotFound from '~/pages/public/NotFound';

// cái này dùng trong Sidebar ở chỗ Button nhé
export const adminRoutes = {
    root: '/moderator',
    dashboard: '/moderator/dashboard',
    account: '/moderator/account-management',
    productLine: '/moderator/product-line',
    setting: '/setting'

}

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
    dashboard: '/factory/dashboard',
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
    toDashboardWithRole: (role: string) => {
        return `/${role.toLowerCase()}/dashboard`;
    },

    public: {
        login: {
            path: '/login',
            element: <Login />
        },

        notFound: {
            path: '/not-found',
            element: <NotFound />
        },
    },

    private: {

        admin: {
            title: 'ADMIN',
            path: adminRoutes.root,
            roles: ['MODERATOR'],
            Sidebar: <SidebarAdmin/>,
            Content: [
                {
                    title: 'Thống kê',
                    path: adminRoutes.dashboard,
                    element: <ContentAdmin.Dashboard />
                },
                {
                    title: 'Quản lý tài khoản',
                    path: adminRoutes.account,
                    element: <ContentAdmin.Account />
                },
                {
                    title: 'Dòng sản phẩm',
                    path: adminRoutes.productLine,
                    element: <ContentAdmin.ProductLine />
                },
            ]
        },

        manufacturingBase: {
            title: 'FACTORY',
            path: factoryRoutes.root,
            roles: ['FACTORY'],
            Sidebar: <SidebarFactory />,
            Content: [
                {
                    title: 'Thống kê',
                    path: factoryRoutes.dashboard,
                    element: <ContentFactory.Dashboard />
                },
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
                    title: 'Chi tiết sản phẩm',
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
                    title: 'Thống kê',
                    path: agencyRoutes.dashboard,
                    element: <ContentAgency.Dashboard />
                },
                {
                    title: 'Nhập sản phẩm mới',
                    path: agencyRoutes.import,
                    element: <ContentAgency.Import />
                },
                {
                    title: 'Thiết bị lỗi',
                    path: agencyRoutes.warranty,
                    element: <ContentAgency.Warranty />
                },
                {
                    title: 'Lô hàng',
                    path: agencyRoutes.order,
                    element: <ContentAgency.Orders />
                },
                {
                    title: 'Nhà kho.',
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