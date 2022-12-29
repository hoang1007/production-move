import React from "react";
import { Outlet, Navigate , useNavigate} from "react-router-dom";
import { toast } from 'react-toastify';

import { useAuth, useAxios } from '~/hooks';
import routes from '~/config/routes';
import api from '~/config/api';
import { actions as authActions } from '~/store/auth';
import Cookies from "js-cookie";

// Just do simple things here :")
// No checking access token in cookies :")
function ProtectedRoutes() {
    console.log('check auth')

    const accessToken = Cookies.get('accessToken');
    if (!accessToken) {
        return <Navigate to={routes.public.login.path} />
    }

    return <Outlet />
}

export default ProtectedRoutes;