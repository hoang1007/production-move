import React from 'react';
import { Outlet, Navigate , useOutletContext} from 'react-router-dom';
import { useAuth } from '~/hooks';
import routes from '~/config/routes';
import { actions as authActions } from '~/store/auth';

interface Prop {
    roles: string[],
}

function RoleBaseRouting({ roles }: Prop) {
    const role = localStorage.getItem("role");

    return (
        <>
            {
                role && roles.includes(role) ?
                    <Outlet />
                    :
                    <Navigate to={routes.public.login.path} replace />
            }
        </>
    );
}

export default RoleBaseRouting;
