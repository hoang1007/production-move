import React from 'react';
import { Outlet, Navigate } from 'react-router-dom';
import { useAuth } from '~/hooks';
import routes from '~/config/routes';

interface Prop {
    roles: string[],
}

function RoleBaseRouting({ roles }: Prop) {
    console.log('check roles')
    const [auth] = useAuth();

    return (
        <>
            {
                // auth?.user?.role && roles.includes(auth.user.role) ?
                    <Outlet />
                    // :
                    // <Navigate to={routes.public.login.path} replace />
            }
        </>
    );
}

export default RoleBaseRouting;
