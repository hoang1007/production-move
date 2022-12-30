import Login from '~/pages/public/Login';

import { SidebarAgency, ContentAgency } from '~/pages/private/Agency'
import { ContentFactory, SidebarFactory } from '~/pages/private/Factory';
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
                    title: 'Dashboard',
                    path: adminRoutes.dashboard,
                    element: <ContentAdmin.Dashboard />
                },
                {
                    title: 'Account Management',
                    path: adminRoutes.account,
                    element: <ContentAdmin.Account />
                },
                {
                    title: 'Product Line Management',
                    path: adminRoutes.productLine,
                    element: <ContentAdmin.ProductLine />
                },
                {
                    title: 'Setting',
                    path: adminRoutes.setting,
                    element: <ContentAdmin.Setting />
                },
            ]
        },

        manufacturingBase: {
            title: '/Factory',
            path: '/factory',
            roles: ['FACTORY'],
            Sidebar: <SidebarFactory />,
            Content: [
                {
                    title: 'Kho',
                    path: '/factory/warehouse',
                    element: <ContentFactory.WarehouseList />
                },
                {
                    title: 'Kho',
                    path: '/factory/warehouse/:id',
                    element: <ContentFactory.Warehouse />
                },
                {
                    title: 'Sản phẩm lỗi',
                    path: '/factory/warranty',
                    element: <ContentFactory.Warranty />
                }
            ]
        },

        agency: {
            title: 'AGENCY',
            path: agencyRoutes.root,
            roles: ['AGENCY'],
            Sidebar: <SidebarAgency/>,
            Content: [
                {
                    title: 'Dashboard',
                    path: agencyRoutes.dashboard,
                    element: <ContentAgency.Dashboard/>
                },
                {
                    title: 'Import new mobile phone',
                    path: agencyRoutes.import,
                    element: <ContentAgency.Import/>
                },
                {
                    title: 'Defective & Repaired mobile phone',
                    path: agencyRoutes.warranty,
                    element: <ContentAgency.Warranty/>
                },
                {
                    title: 'Orders',
                    path: agencyRoutes.order,
                    element: <ContentAgency.Orders/>
                },
                 {
                    title: 'All warehouses',
                    path: agencyRoutes.warehouse,
                    element: <ContentAgency.Warehouse/>
                },
            ]
        },

        warranty: {
            path: '/warranty',
            roles: [],
            Sidebar: <></>,
            Content: []
        }

    }
}

export default routes;