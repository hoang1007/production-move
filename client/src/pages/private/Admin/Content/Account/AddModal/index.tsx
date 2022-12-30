import React, { ChangeEvent } from 'react';
import {toast} from 'react-toastify'

import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import { EyeIcon, EyeSlashIcon} from '~/components/Icon'
import { Button, FormControl, IconButton, InputAdornment, InputLabel, MenuItem, OutlinedInput, Select, SelectChangeEvent, TextField } from '@mui/material';
import { trimObject } from '~/utils/helper';
import { useAxios } from '~/hooks';
import api from '~/config/api'

const cx = ClassNames(style);

interface AccountInput {
    username: string,
    password: string,
    role: string,
    name: string,
    address: string
}

const init: AccountInput = {
    username: '',
    password: '',
    role: '',
    name: '',
    address: ''
}

function AddModal() {
    const [showPassword, setShowPassword] = React.useState(false);
    const [account, setAccount] = React.useState<AccountInput>(init);
    const axios = useAxios()

    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    };

    const validate = (acc: AccountInput): boolean => {
        if (!acc.password || !acc.role  || !acc.username
            || !acc.address || !acc.name
        ) {
            return false
        }

        return true
    }

    const reset = () => {
        setAccount(init)
    }
    
    const createAccount = () => {
        if (account && account.password.length < 8) {
            toast.error("Mật khẩu quá ngắn. Ít nhất phải có 8 ký tự.")
            return false
        }
        axios.post(api.moderator.createAccount, trimObject(account))
            .then(res => {
                if (res.status === 200) {
                    toast.success('Tạo mới thành công!')
                    reset()
                }
            }).catch(err => {
                console.log(err)
                toast.error(err.response.data.message || err.message)
            })
    }

    return (
        <div className={cx('container')}>
            <h2 className={cx('title')}>Tạo tài khoản mới</h2>
            <div className={cx('form-create')}>
                <TextField
                    className={cx('input')}
                    required
                    id="outlined-required"
                    label="Tên tài khoản"
                    fullWidth
                    value={account?.username}
                    onChange={e => setAccount({
                        ...account,
                        username: e.target.value
                    })}
                />
                <FormControl variant="outlined" fullWidth>
                    <InputLabel htmlFor="outlined-adornment-password">Mật khẩu</InputLabel>
                    <OutlinedInput
                        id="outlined-adornment-password"
                        type={showPassword ? 'text' : 'password'}
                        value={account.password}
                        onChange={(e: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) =>
                            setAccount({
                                ...account,
                                password: e.target.value
                            })
                        }
                        endAdornment={
                            <InputAdornment position="end">
                                <IconButton
                                    aria-label="toggle password visibility"
                                    onMouseDown={handleMouseDownPassword}
                                    edge="end"
                                    onClick={() => setShowPassword(pre => !pre)}
                                >
                                    {showPassword ? <EyeSlashIcon /> : <EyeIcon />}
                                </IconButton>
                            </InputAdornment>
                        }
                        label="Mật khẩu"
                    />
                </FormControl>


                <FormControl fullWidth className={cx('input')} required>
                    <InputLabel id="demo-simple-select-label">Loại tài khoản sử dụng</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={account?.role || 'NONE'}
                        label="Loại tài khoản sử dụng"
                        onChange={(e: SelectChangeEvent<string>) =>
                            setAccount({
                                ...account,
                                role: e.target.value
                            })
                        }
                    >
                        <MenuItem value={'AGENCY'}>Đại lý</MenuItem>
                        <MenuItem value={'FACTORY'}>Nhà máy sản xuất</MenuItem>
                        <MenuItem value={'WARRANTY'}>Trung tâm bảo hành</MenuItem>
                    </Select>
                </FormControl>
                <TextField
                    className={cx('input')}
                    required
                    id="outlined-required"
                    label="Tên bộ phận"
                    fullWidth
                    value={account?.name}
                    onChange={e => setAccount({
                        ...account,
                        name: e.target.value
                    })}
                />
                <TextField
                    className={cx('input')}
                    required
                    id="outlined-required"
                    label="Địa chỉ"
                    fullWidth
                    value={account?.address}
                    onChange={e => setAccount({
                        ...account,
                        address: e.target.value
                    })}
                />

                <div className={cx('actions')}>
                    <Button
                        variant="outlined"
                        onClick={reset}
                    >Tạo lại từ đầu</Button>

                    <Button variant="contained"
                        onClick={createAccount}
                        disabled={!validate(account)}
                    >Bắt đầu tạo mới</Button>
                </div>
            </div>
        </div>
    );
}

export default AddModal;