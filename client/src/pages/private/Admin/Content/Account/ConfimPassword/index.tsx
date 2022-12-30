import React from 'react';
import { toast } from 'react-toastify';

import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import {EyeIcon, EyeSlashIcon} from '~/components/Icon';
import { Button, IconButton, Input, TextField } from '@mui/material';
import { LoadingButton } from '@mui/lab';
import { useAxios } from '~/hooks';
import api from '~/config/api';
import { AccountType } from '~/utils/TypeGlobal';
const cx = ClassNames(style);

interface Props {
    title?: string,
    close: Function,
    closeSuccess: Function,
    id?: string,
    className?:string
}

function ConfirmPassword({title, className, close, closeSuccess, id }: Props) {
    const [password, setPassword] = React.useState<string>('');
    const [loading, setLoading] = React.useState<boolean>(false)
    const axios = useAxios();

    const confirm = () => {
        if (!password.trim()) {
            toast.error('Mật khẩu không được để trống.')
            return
        }
        
        setLoading(true)
        axios.post(api.moderator.checkPassword, {
            password,
        })
            .then(res => {
                if (res.status === 200) {
                    closeSuccess()
                    setLoading(false)
                }
            }).catch(err => {
                console.log(err)
                setLoading(false)
                if (err.response.status === 401) {
                    toast.error('Mật khẩu không chính xác.')
                    return;
                }
                toast.error(err.message)
                
            })

    }

    return (
        <div className={[cx('container'), className].join(' ')} id={id}>
            {
                title && <h1 className={cx('title')}>{title}</h1>
            }
            <h3>Nhập mật khẩu để tiếp tục</h3>
            <TextField
                fullWidth={true}
                label="password"
                type="password"
                value={password}
                onChange={e => setPassword(e.target.value.trim())}
            />
            <div className={cx('actions')}>
                <Button
                    className={cx('btn', 'btn-cancel')}
                    variant="outlined"
                    onClick={() => {
                        close()
                        setPassword('')
                    }}
                >Hủy</Button>
                <LoadingButton
                    className={cx('btn', 'btn-confirm')}
                    variant="contained"
                    onClick={confirm}
                    loading={loading}
                >
                    Xác nhận
                </LoadingButton>
            </div>
        </div>
    );
}

export default ConfirmPassword;