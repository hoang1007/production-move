import React from 'react';
import { useNavigate } from 'react-router';
import { toast } from 'react-toastify';
import { useAuth, useAxios } from '~/hooks';
import api from '~/config/api';
import WarehouseItem from './WarehouseItem';
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import Loading from '~/components/Loading';
import { ProductType, WarehouseType } from '~/utils/TypeGlobal';
import routes from '~/config/routes'
import { Button } from '@mui/material';
import { BackIcon } from '~/components/Icon'
import ProductLineItem from '~/pages/components/ProductLine';

const cx = ClassNames(style);

function Warehouse() {
    const [auth] = useAuth();
    const axios = useAxios();
    const [listWarehouse, setListWarehouses] = React.useState<WarehouseType[]>([]);
    const [loading, setLoading] = React.useState<boolean>(true)
    const [currentProduct, setCurrentProduct] = React.useState<ProductType | null>(null);
    const navigate = useNavigate()

    React.useEffect(() => {
        axios.get(api.agency.allWarehouses, {
            params: {
                agencyId: auth?.user.id
            }
        })
            .then(response => {
                if (response.status === 200) {
                    setListWarehouses(response.data);
                    setTimeout(() => {
                        setLoading(false)
                    }, 1200)
                }
            })
            .catch(error => {
                console.log(error)
                setLoading(false)
                if (error.response.status === 401) {
                    return navigate(routes.public.login.path)
                }
                toast.error(error.message)
            })
    }, [])

    return (
        <div className={cx('container')}>
            {
                currentProduct === null ? (

                    !loading ?
                        listWarehouse.map((warehouse: WarehouseType, index: number) => {
                            return (
                                <WarehouseItem
                                    key={`warehouse-${warehouse.id}-index-${index}`}
                                    data={(warehouse)}
                                    setCurrentProduct={setCurrentProduct}
                                />
                            )
                        })
                        :
                        <Loading />
                )
                    :
                    <div className={cx('detail')}>
                        <div className={cx('actions')}>
                            <Button className={cx('back')}
                                onClick={() => setCurrentProduct(null)}
                            > <BackIcon /> </Button>
                        </div>
                        <div className={cx('pdl-detail')}>
                            <ProductLineItem product={currentProduct.productline} />
                        </div>
                    </div>
            }
        </div>
    );
}

export default Warehouse;