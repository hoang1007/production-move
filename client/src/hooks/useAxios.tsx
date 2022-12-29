import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios, { AxiosRequestConfig } from 'axios';
import { useAuth } from '~/hooks';
import routes from '~/config/routes';
import Cookies from 'js-cookie';

const instance = axios.create({
    baseURL: process.env.REACT_APP_BACKEND_BASE_URL,
    withCredentials: true,
    headers: {
        // 'Content-Type': 'application/'
    }
    // timeout: 3000,
})

const usePrivateAxios = () => {
    const [auth] = useAuth();
    const navigate = useNavigate();
    const username = auth?.user.username;

    useEffect(() => {
        const requestIntercept = instance.interceptors.request.use((config: AxiosRequestConfig<any>) => {
            if (config?.headers &&  !config.headers['Authorization']) {
                config.headers['Authorization'] = `Bearer ${auth?.accessToken || Cookies.get('accessToken') || ''}`;
            }
            return config;
        }, (err) => {
            return Promise.reject(err);
        })

        const responseIntercept = instance.interceptors.response.use((response: any) => {
            if (response.status === 403) {
                navigate(routes.public.login, { replace: true })
                return;
            }
            return response;
        }, (err) => {
            return Promise.reject(err);
        })

        return () => {
            instance.interceptors.request.eject(requestIntercept)
            instance.interceptors.response.eject(responseIntercept)
        }
    }, [username]);

    return instance;
}

export default usePrivateAxios;
