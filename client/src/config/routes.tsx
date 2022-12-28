import React from 'react';
import Login from '~/pages/public/Login';

import { SidebarAgency, ContentAgency } from '~/pages/private/Agency'

// cái này dùng trong Sidebar ở chỗ Button nhé
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
            path: '/manufacturingBase',
            roles: [],
            Sidebar: <></>,
            Content: []
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