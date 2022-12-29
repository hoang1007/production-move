import React from "react";
import { Outlet, Navigate , useNavigate} from "react-router-dom";
import { toast } from 'react-toastify';

import { useAuth, useAxios } from '~/hooks';
import routes from '~/config/routes';
import api from '~/config/api';
import { actions as authActions } from '~/store/auth';

// Just do simple things here :")
// No checking access token in cookies :")
function ProtectedRoutes() {
    console.log('check auth')
    const [auth, authDispatch] = useAuth();
    const axios = useAxios();
    const navigate = useNavigate();
    const [loading, setLoading] = React.useState(true);

    React.useEffect(() => {
        // if (auth?.isLoggedIn) {
        //     setLoading(false);
        //     return;
        // }

        // axios.post(api.login, {username: '', password: ''}).then(res => {
        //     if (res.status === 200) {
        //         if (authDispatch) {
        //             authDispatch(authActions.login(res.data));
        //         }

        //         setTimeout(() => {
        //             navigate(routes.toDashboardWithRole(res.data.role))
        //             setLoading(false)
        //         }, 1200)
        //     } else {
        //         toast.error("User name or password is incorrect!");
        //         setLoading(false)
        //     }
        // }).catch(error => {
        //     console.log(error)
        //     setLoading(false)
        //     if (error.response.status === 401) {
        //         toast.error("User name or password is incorrect!");
        //         return;
        //     } else {
        //         toast.error(error.message);
        //     }
        // })

    }, [])

    // return !loading ? <Outlet /> : <Navigate to={routes.public.login.path}></Navigate>
    return auth?.isLoggedIn ? <Outlet /> : <Navigate to={routes.public.login.path}></Navigate>
}

export default ProtectedRoutes;