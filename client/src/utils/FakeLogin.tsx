import React from 'react';

import { useAxios } from '~/hooks';

function Login() {
    const axios = useAxios();

    React.useEffect(() => {
        axios.post("/api/login", {
            username: 'abcd',
            password: '1234',
        }).then(res => {
            console.log(res)
        }).catch(error => {
            console.log(error)
        })
    }, [])

    return (
        <div>Login</div>
    );
}

export default Login;