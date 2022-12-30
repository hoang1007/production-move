import React from 'react';

import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import { AccountType } from '~/utils/TypeGlobal';
import {EyeIcon, EyeSlashIcon} from '~/components/Icon';
import { Button, IconButton, Input, TextField } from '@mui/material';
import ConfirmPassword from '../ConfimPassword';
import { useAxios } from '~/hooks';
import api from '~/config/api';
import { toast } from 'react-toastify';
const cx = ClassNames(style);

interface Props {
    className?: string,
    account: AccountType,
    toggleShowModalEdit: Function
}

function EditModal({ className, account, toggleShowModalEdit }: Props) {
    const [currentAccount, setCurrentAccount] = React.useState<AccountType>({...account});
    const [showPassword, setShowPassword] = React.useState(false)
    const [editing, setEditing] = React.useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = React.useState(false);
    const axios = useAxios();

    const change = (e: React.ChangeEvent<HTMLInputElement>, type: string) => {
        setCurrentAccount(prev => ({
            ...prev,
            [type]: e.target.value
        }))
    }

    const save = () => {
        if (!currentAccount.password) {
            toast.error("Mật khẩu không được để trống")
            return
        }

        if (currentAccount.password.length < 8) {
            toast.error("Mật khẩu quá ngắn, ít nhất 8 ký tự");
            return
        }

        axios.put(api.moderator.updateAccount, {
            username: currentAccount.username,
            password: currentAccount.password
        })
            .then((res: { status: number; }) => {
                if (res.status === 200) {
                    toast.success("Cập nhật thành công!");
                }
            }).catch(error => {
                console.log(error)
                toast.error('Không thể cập nhật.')
            })
    }

    const close = () => {
        setCurrentAccount(account);
        toggleShowModalEdit()
        setEditing(false)
    }

    return (
        <div id="modal" className={[cx('container'), className].join(' ')}>
            <div className={cx('body')}>
                <div className={cx('info')}>
                    <h2 className={cx('role')}>{currentAccount.role}</h2>
                    <div className={cx('username')}>Tên: {currentAccount.username}</div>
                    <span className={cx('address')}>Địa chỉ: {currentAccount.user.address}</span>
                    <div
                        className={cx('password')}
                    >
                        Mật khẩu:
                        <input
                            className={cx('input', { onlyShow: !editing })}
                            type={showPassword ? 'text' : 'password'}
                            value={editing ? currentAccount.password :'*'.repeat(8)}
                            onChange={e => {
                                change(e, 'password')
                            }}
                        />

                        <IconButton className={cx('eye')} onClick={() => setShowPassword(pre => !pre)}>
                            {
                                editing ?
                                    showPassword ? <EyeSlashIcon /> : <EyeIcon />
                                    :
                                    <></>
                            }
                        </IconButton>

                    </div>
                </div>

                <div className={cx('actions')}>
                    {
                        showConfirmPassword ?
                            <ConfirmPassword
                                close={() => {
                                    setShowConfirmPassword(false)
                                }}
                                closeSuccess={() => {
                                    setShowConfirmPassword(false)
                                    setEditing(true)
                                }}
                            />
                            :
                            <>
                                <Button className={cx('btn', 'btn-cancel')} variant="outlined" onClick={close}>Cancel</Button>
                                <Button
                                    className={cx('btn', 'btn-save')}
                                    variant="contained"
                                    onClick={() => {
                                        if (editing) save()
                                        else setShowConfirmPassword(true)
                                    }}
                                >
                                    {editing ? 'Lưu' : 'Chỉnh sửa'}
                                </Button>
                            </>
                    }
                </div>
            </div>
        </div>
    );
}

export default EditModal;