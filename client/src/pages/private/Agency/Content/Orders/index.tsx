import React from 'react';
import { toast } from 'react-toastify';

import Loading from '~/components/Loading';
import { useAxios , useAuth, useModal} from '~/hooks';
import api from '~/config/api';
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import { CustomerType } from '~/utils/TypeGlobal';
import { Button,Radio } from '@mui/material';
import OrderModal from './OrderModal';
const cx = ClassNames(style);

function Orders() {
    const axios = useAxios();
    const [auth] = useAuth()
    const [loading, setLoading] = React.useState<boolean>(true)
    const [customers, setCustomers] = React.useState<CustomerType[]>([]);
    const [dataModal, setDataModal] = React.useState<CustomerType | null>(null);
    const [isSold, setIsSold] = React.useState<boolean>(false);
    const [soldCustomer, setSoldCustomer] = React.useState<number[]>([])

    React.useEffect(() => {
        axios.get(`${api.agency.getOrders}`, {
            params: {agencyId: auth?.user.agencyId}
        })
            .then(response => {
                console.log(response)
                if (response.status === 200) {
                    setCustomers(response.data)
                    setTimeout(() => {
                        setLoading(false)
                    }, 1200)
                }

            }).catch(error => {
                console.log(error)
                setLoading(false)
                toast.error(error.message)
            })
    }, [])

    React.useEffect(() => {
        const soldCus: number[] = [];
        customers.forEach(customer => {
            customer.orders.forEach(order => {
                if (order.soldDate !== null) {
                    soldCus.push(customer.id)
                }
            })
        })
        setSoldCustomer(soldCus)
    }, [customers.length])

    const handleSeeDetailOrder = (customerId: number) => {
        setDataModal(customers.find(customer => customer.id === customerId) || null);
    }

    const handleSell = (customerId:number) => {
        if (!soldCustomer.includes(customerId)) {
            soldCustomer.push(customerId);
        }
    }

    const filter = (is_sold: boolean) => {
        return customers.filter(customer => {
            if (is_sold) {
                return soldCustomer.includes(customer.id)
            } else {
                return !soldCustomer.includes(customer.id)
            }
        });
    }

    return (
        <div style={{height: '100%'}}>
            <div className={cx('filter')}>
                <span className={cx('title')}>Sold:</span>
                <Radio
                    checked={isSold}
                    onChange={() => { setIsSold(true) }}
                    name="radio-buttons"
                />  
                <span className={cx('title')}>Pending:</span>
                <Radio
                    checked={!isSold}
                    onChange={() => { setIsSold(false) }}
                    name="radio-buttons"
                />
            </div>
            <div className={cx('container')}>
                {
                    loading ?
                        <Loading className={cx('loading')} />
                        :
                        <>
                            {
                                filter(isSold).length === 0 ?
                                    <div>No Orders</div>
                                    :
                                    <>
                                        {
                                            filter(isSold).map((customer: CustomerType, index: number) => {
                                                return (
                                                    <div key={`customer-${customer.id}-index-${index}`} className={cx('customer-item')}>
                                                        <div className={cx('customer')}>
                                                            <h2>Customer</h2>
                                                            <div>Name: {customer.fullname}</div>
                                                            <div>Email: {customer.email}</div>
                                                            <div>Phone number: {customer.phoneNumber || '__'}</div>
                                                        </div>

                                                        <div className={cx('orders')}>
                                                            <div>Orders: </div>
                                                            <span>{customer.orders.length}</span>
                                                        </div>

                                                        <Button
                                                            className={cx('btn-see-detail')}
                                                            variant='contained'
                                                            onClick={() => handleSeeDetailOrder(customer.id)}
                                                        >See detail</Button>
                                                    </div>
                                                )
                                            })
                                        }
                                    </>
                            }

                            {
                                dataModal !== null &&
                                <OrderModal
                                    customer={dataModal}
                                    close={() => setDataModal(null)}
                                    sell={handleSell}
                                    type={isSold}
                                />
                            }
                        </>
                }
            </div>
        </div>
    );
}

export default Orders;