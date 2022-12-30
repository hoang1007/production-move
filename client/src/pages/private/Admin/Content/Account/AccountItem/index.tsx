import React from 'react';
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import { AccountType } from '~/utils/TypeGlobal';
import { Button } from '@mui/material';
import EditModal from '../EditModal';
const cx = ClassNames(style);

interface Props {
    className?: string,
    account: AccountType
}

function AccountItem({ className, account }: Props) {
    const [showEditModal, setShowEditModal] = React.useState(false);

    const toggleShowModalEdit = () => {
        setShowEditModal(pre => !pre)
    }
    
    return (
        <div className={[cx('container'), className].join(' ')}>
            <div className={cx('info')}>
                <h2 className={cx('role')}>{account.role}</h2>
                <span className={cx('username')}>Name: {account.username}</span>
                <span className={cx('address')}>Address: {account.user.address}</span>
                <div
                    className={cx('password')}
                >
                    Password: {'*'.repeat(8)}
                </div>
            </div>

            <div className={cx('actions')}>
                <Button
                    size='large'
                    className={cx('btn-see-edit', 'btn')}
                    onClick={toggleShowModalEdit}
                >Edit</Button>
            </div>

            {
                showEditModal &&
                <EditModal className={cx('edit-modal')} account={account} toggleShowModalEdit={toggleShowModalEdit} />
            }
        </div>
    );
}

export default AccountItem;