import React from 'react';
import Login from '~/pages/public/Login';

import {SidebarAgency, ContentAgency} from '~/pages/private/Agency'

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
            path: '/agency',
            roles: [],
            Sidebar: <SidebarAgency/>,
            Content: [
                {
                    title: 'Dashboard',
                    path: '/agency/dashboard',
                    element: <ContentAgency.Dashboard/>
                },
                {
                    title: 'Import new mobile phone',
                    path: '/agency/import',
                    element: <ContentAgency.Import/>
                },
                {
                    title: 'Defective & Repaired mobile phone',
                    path: '/agency/warranty',
                    element: <ContentAgency.Warranty/>
                },
                {
                    title: 'Orders',
                    path: '/agency/order',
                    element: <ContentAgency.Order/>
                },
                {
                    title: 'Your stock',
                    path: '/agency/stock',
                    element: <ContentAgency.Stock/>
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

// cái này dùng trong Sidebar ở chỗ Button nhé
export const agencyRoutes = {
    dashboard: '/agency/dashboard',
    import: '/agency/import',
    warranty: '/agency/warranty',
    order: '/agency/order',
    stock: '/agency/stock',
}

export default routes;