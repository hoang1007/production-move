import Login from '~/pages/public/Login';

import { SidebarAgency, ContentAgency } from '~/pages/private/Agency'
import { ContentFactory, SidebarFactory } from '~/pages/private/Factory';
import { ContentAdmin, SidebarAdmin } from '~/pages/private/Admin';
import NotFound from '~/pages/public/NotFound';

// cái này dùng trong Sidebar ở chỗ Button nhé
export const adminRoutes = {
    root: '/admin',
    dashboard: '/admin/dashboard',

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
            path: adminRoutes.root,
            roles: ['MODERATOR', 'ADMIN'],
            Sidebar: <SidebarAdmin/>,
            Content: [
                {
                    title: 'Dashboard',
                    path: adminRoutes.dashboard,
                    element: <ContentAdmin.Dashboard />
                },
            ]
        },

        manufacturingBase: {
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