import React from 'react';
import Login from '~/pages/public/Login';

import Agency from '~/pages/private/Agency';

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
            element: <></>,
            roles: []
        },

        manufacturingBase: {
            path: '/manufacturingBase',
            element: <></>,
            roles: []
        },

        agency: {
            path: '/agency', // mặc định vào luôn dashboard
            element: <Agency/>,
            roles: [],
            children: [
                {
                    path: '/import',
                    element: <></>
                },
                {
                    path: '/warranty',
                    element: <></>
                },
                {
                    path: '/order',
                    element: <></>
                },
                {
                    path: '/stock',
                    element: <></>
                }
            ]
            
        },

        warranty: {
            path: '/warranty',
            element: <></>,
            roles: []
        }

    }
}

export default routes;