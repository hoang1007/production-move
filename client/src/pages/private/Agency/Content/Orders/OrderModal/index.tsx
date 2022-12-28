import React from 'react';
import { toast } from 'react-toastify';

import api from '~/config/api';
import { useAxios } from '~/hooks';
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import { CustomerType } from '~/utils/TypeGlobal';
import { Button } from '@mui/material';
import LoadingButton from '@mui/lab/LoadingButton';
const cx = ClassNames(style);

interface Props {
    customer: CustomerType,
    sell: Function,
    close: Function,
    type: boolean
}

function OrderModal({ customer, type, close, sell }: Props) {
    const axios = useAxios();
    const [loading, setLoading] = React.useState(false);

    const handleSell = () => {
        setLoading(true)
        axios.post(api.agency.sellProduct, {
            customerId: customer.id,
            productIds: customer.orders.map(order => order.product.id)
        }).then(response => {
            if (response.status === 200) {
                setTimeout(() => {
                    setLoading(false)
                    sell(customer.id)
                    close()
                    toast.success('Sell successfully');
                }, 1500);
            }
        }).catch(error => {
            setLoading(false)
            console.log(error);
            toast.error(error.message);
        })
    }

    return (
        <div id="modal" className={cx('container')}>
            <div className={cx('body')}>
                <div>
                    <div className={cx('customer')}>
                        <h2>Customer{customer.id}</h2>
                        <div>Name: {customer.fullname}</div>
                        <div>Email: {customer.email}</div>
                        <div>Phone number: {customer.phoneNumber || '__'}</div>
                    </div>
                </div>

                <div className={cx('actions')}>
                    <Button
                        variant="text"
                        className={cx('close')}
                        onClick={() => close()}
                    >Close</Button>
                    {
                        !type &&
                        <LoadingButton
                            className={cx('sell')}
                            size="small"
                            onClick={handleSell}
                            loading={loading}
                            variant="contained"
                        >Sell</LoadingButton>
                    }
                </div>
            </div>
        </div>
    );
}

export default OrderModal;