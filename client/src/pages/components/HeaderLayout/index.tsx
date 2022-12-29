import React from 'react';
import { useNavigate } from 'react-router-dom';
import {toast} from 'react-toastify'

import { SearchIcon, NotificationIcon } from '~/components/Icon'
import Input from '~/components/Input';
import Image from '~/components/Image';
import { useAuth, useAxios } from '~/hooks';
import { actions as authActions } from '~/store/auth';
import routes from '~/config/routes'
import api from '~/config/api';
import Loading from '~/components/Loading'
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import { Button } from '@mui/material';
const cx = ClassNames(style);

interface Props {
    className?: string
}

function Header({ className }: Props) {
    const [auth, authDispatch] = useAuth()
    const [active, setActive] = React.useState(false);
    const navigate = useNavigate()
    const axios = useAxios()
    const [loading, setLoading] = React.useState(false);

    const toggleActive = () => {
        setActive(pre => !pre);
    }

    const handleLogout = () => {
        setLoading(true)
        axios.get(api.logout)
            .then((res) => {
                console.log(res)
                if (res.status === 200) {
                    if (authDispatch) {
                        authDispatch(authActions.logout());
                        console.log(routes.public.login.path)
                        navigate(routes.public.login.path);
                        setLoading(false)
                    }
                }
            }).catch(err => {
                console.log('Loi: ',err)
                toast.error('Cannot logout.');
                setLoading(false)
            })
    }

    const classes = [cx('container'), 'header-layout', className].join(' ')

    return (
        <div className={classes}>
            <Input
                className={cx('search-input')}
                icon={<SearchIcon className={cx('icon-search')} />}
                position='start'
            />
            
            <div className={cx('actions')}>
                <div className={cx('noti')}>
                    <NotificationIcon className={cx('icon')} />
                    <span className={cx('no-noti')}>2</span>
                </div>

                <div className={cx('user', { active: active })} onClick={toggleActive}>
                    {
                        !loading ?
                            <>
                                <Image className={cx('avatar')} />
                                <span className={cx('username')}>{auth?.user.username}</span>
                                {
                                    active && <div className={cx('more')}>
                                        <Button
                                            className={cx('btn-logout')}
                                            onClick={handleLogout}
                                        >
                                            Log out
                                        </Button>
                                    </div>
                                }
                            </>
                            :
                            <Loading />
                    }
                </div>

            </div>
        </div>
    );
}

export default Header;