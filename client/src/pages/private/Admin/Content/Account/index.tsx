import React from 'react';
import { Radio } from '@mui/material';
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';

import { AccountType } from '~/utils/TypeGlobal';
import { useAxios } from '~/hooks';
import api from '~/config/api';
import routes from '~/config/routes';
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import AccountItem from './AccountItem';
const cx = ClassNames(style);

function Account() {
    const [filter, setFilter] = React.useState<'all' | 'agency' | 'factory' | 'warranty'>('all');
    const [accounts, setAccounts] = React.useState<AccountType[]>([]);
    const axios = useAxios();
    const navigate = useNavigate();

    React.useEffect(() => {
        axios.get(api.moderator.allAccounts)
            .then(res => {
                if (res.status === 200) {
                    setAccounts(res.data);
                }
            }).catch(error => {
                console.log(error)
                if (error.response.status === 401) {
                    navigate(routes.public.login.path);
                }
                toast.error(error.message);
            })
    }, [])

    const selector = (listAcc: AccountType[]) => {
        if (filter === 'all') return listAcc;
        return listAcc.filter(acc => acc.role.toLowerCase() === filter);
    }

    return (
        <div className={cx('container')}>
            <div className={cx('filter')}>
                <span className={cx('title')}>All:</span>
                <Radio
                    checked={filter === 'all'}
                    onChange={() => { setFilter('all') }}
                    name="radio-buttons"
                />
                
                <span className={cx('title')}>Agency:</span>
                <Radio
                    checked={filter === 'agency'}
                    onChange={() => { setFilter('agency') }}
                    name="radio-buttons"
                />

                <span className={cx('title')}>Factory:</span>
                <Radio
                    checked={filter === 'factory'}
                    onChange={() => { setFilter('factory') }}
                    name="radio-buttons"
                />

                <span className={cx('title')}>Warranty:</span>
                <Radio
                    checked={filter === 'warranty'}
                    onChange={() => { setFilter('warranty') }}
                    name="radio-buttons"
                />
            </div>

            <div className={cx('info-general')}>
                <span>Total: {selector(accounts).length}</span>
            </div>

            <div className={cx('body')}>
                {
                    selector(accounts).length === 0 ?
                        <div className="no-accounts">No accounts</div>
                        :
                        selector(accounts).map((account, index) => {
                            return <AccountItem  key={`account-${account.user.id}-index-${index}`} className={cx('account-item')} account={account}/>
                        })
                }
            </div>
        </div>
    );
}

export default Account;