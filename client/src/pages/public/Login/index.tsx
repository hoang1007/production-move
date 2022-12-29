import React from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

import { useAxios, useAuth } from '~/hooks';
import { Button, TextField } from '@mui/material';
import background from '~/assets/images/bgc-login.jpg';
import Loading from '~/components/Loading';
import api from '~/config/api';
import routes from '~/config/routes';
import { validateUsername, validatePassword } from '~/utils/validator';

import { actions as authActions } from '~/store/auth';

import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
const cx = ClassNames(style);

function Login() {
    const axios = useAxios();
    const [username, setUsername] = React.useState<string>('');
    const [password, setPassword] = React.useState<string>('');
    const [loading, setLoading] = React.useState<boolean>(false);
    const [auth, authDispatch] = useAuth();
    const navigate = useNavigate();

    const changeUsername = (e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
        e.target.value = e.target.value.trim();
        setUsername(e.target.value)
    }
    
    const changePassword = (e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
        e.target.value = e.target.value.trim();
        setPassword(e.target.value)
    }

    const handleLogin = () => {
        const resUsername = validateUsername(username)
        const resPassword = validatePassword(password)

        if (resUsername?.error) {
            toast.error(resUsername.message)
            return
        }
        
        // if (resPassword?.error) {
        //     toast.error(resPassword.message)
        //     return
        // }

        setLoading(true)
        axios.post(api.login, {
            username: username,
            password: password,
        }).then(res => {
            if (res.status === 200) {
                if (authDispatch) {
                    authDispatch(authActions.login(res.data));
                }

                setTimeout(() => {
                    setLoading(false)
                    navigate(routes.toDashboardWithRole(res.data.role))
                }, 1200)
            } else {
                toast.error("User name or password is incorrect!");
            }
        }).catch(error => {
            console.log(error)
            setLoading(false)
            if (error.response.status === 401) {
                toast.error("User name or password is incorrect!");
                return;
            } else {
                toast.error(error.message);
            }
        })
    }

    return (
        <div className={cx("container")} data-bgc={background}>
            <h1 className={cx('name-company')}>Welcome to BigCorp</h1>
            <div className={cx("form")}>
                <div className={cx('title')}>LOG IN</div>
                <TextField
                    className={cx('username')}
                    id="filled-search"
                    label="User name"
                    type="text"
                    variant="filled"
                    onChange={changeUsername}
                    defaultValue={username}
                    />
                <TextField
                    className={cx('password')}
                    id="outlined-password-input"
                    label="Password"
                    type="password"
                    onChange={changePassword}
                    defaultValue={password}
                />

                <Button
                    className={cx('btn-login')}
                    color="secondary"
                    onClick={handleLogin}
                >
                    {
                        loading ? 
                            <Loading className={cx('btn-loading')}/>
                            :
                            <span>Log in</span>
                    }
                </Button>
            </div>
        </div>
    );
}

export default Login;