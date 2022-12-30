import React from 'react';
import {toast} from'react-toastify'
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import { AccountType } from '~/utils/TypeGlobal';
import { Button } from '@mui/material';
import EditModal from '../EditModal';
import ConfirmPassword from '../ConfimPassword';
import { useAxios } from '~/hooks';
import api from '~/config/api';
import { getRoleInVietNamese } from '~/utils/helper';
const cx = ClassNames(style);

interface Props {
    className?: string,
    account: AccountType,
    listAccountController: Function
}

function AccountItem({ className, account, listAccountController }: Props) {
    const [showEditModal, setShowEditModal] = React.useState(false);
    const [showConfirmModal, setShowConfirmModal] = React.useState(false);
    const axios = useAxios();

    const toggleShowModalEdit = () => {
        setShowEditModal(pre => !pre)
    }

    const handleDelete = () => {
        axios.delete(api.moderator.deleteAccount, { 
            params: {
                username: account.username
            }
         })
            .then(res => {
                if (res.status === 200) {
                    toast.success('Xóa thành công.')
                    listAccountController((prevList: AccountType[]) => prevList.filter(acc => acc.username !== account.username))
                }
            }).catch(err => {
                console.log(err)
                toast.error(err.message)
        })
    }
    
    return (
        <div className={[cx('container'), className].join(' ')}>
            <div className={cx('info')}>
                <h2 className={cx('role')}>
                    {getRoleInVietNamese(account.role)}
                </h2>
                <span className={cx('username')}>Tên: {account.username}</span>
                <span className={cx('address')}>Địa chỉ: {account.user.address}</span>
                <div
                    className={cx('password')}
                >
                    Mật khẩu: {'*'.repeat(8)}
                </div>
            </div>

            <div className={cx('actions')}>
                <Button
                    size='large'
                    className={cx('btn-see-edit', 'btn')}
                    onClick={toggleShowModalEdit}
                >Chỉnh sửa</Button>
                <Button
                    size='large'
                    className={cx('btn-see-edit', 'btn')}
                    onClick={() => setShowConfirmModal(true)}
                >Xóa</Button>

                {
                    showConfirmModal &&
                    <div id="modal">
                        <ConfirmPassword
                            title={`Xóa tài khỏa của ${getRoleInVietNamese(account.role)}: ${account.username}`}
                            className={cx('confirm-modal')}
                            close={() => setShowConfirmModal(false)}
                            closeSuccess={handleDelete}
                        />
                    </div>
                }
                            
            </div>

            {
                showEditModal &&
                <EditModal className={cx('edit-modal')} account={account} toggleShowModalEdit={toggleShowModalEdit} />
            }
        </div>
    );
}

export default AccountItem;