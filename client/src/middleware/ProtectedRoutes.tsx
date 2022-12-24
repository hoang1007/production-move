import React from "react";
import { Outlet } from "react-router-dom";

import { useAuth } from '~/hooks';

function ProtectedRoutes() {
    console.log('check auth')
    const [auth] = useAuth();
    const [loading, setLoading] = React.useState(true)

    // React.useEffect(() => {
    //     if (auth?.isLoggedIn) {
    //         setLoading(false);
    //         return;
    //     }

        /**
         * NEXT: 
         * Call api to get basic info if it already has access_token in cookies
         * If access_token is invalid or expired, navigate to login page.
         * 
         * if everything is OK, go ahead
         */


    // }, [])

    return <Outlet/>
}

export default ProtectedRoutes;