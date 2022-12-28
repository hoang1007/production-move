import React from 'react';
import { toast } from 'react-toastify';
import { useAuth, useAxios } from '~/hooks';
import api from '~/config/api';
import WarehouseItem from './WarehouseItem';
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import Loading from '~/components/Loading';
import { WarehouseType } from '~/utils/TypeGlobal';
// import { importedProductsSelector } from '~/utils/selector';

const cx = ClassNames(style);

function Warehouse() {
    const [auth] = useAuth();
    const axios = useAxios();
    const [listWarehouse, setListWarehouses] = React.useState<WarehouseType[]>([]);
    const [loading, setLoading] = React.useState<boolean>(true)

    React.useEffect(() => {
        axios.get(api.agency.allWarehouses, {
            params: {
                agencyId: auth?.user.agencyId
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
                toast.error(error.message)
            })
    }, [])

    return (
        <div className={cx('container')}>
            {
                !loading ?
                    listWarehouse.map((warehouse: WarehouseType, index: number) => {
                        return (
                            <WarehouseItem key={`warehouse-${warehouse.id}-index-${index}`} data={(warehouse)} />
                        )
                    })
                    :
                    <Loading />
            }
        </div>
    );
}

export default Warehouse;