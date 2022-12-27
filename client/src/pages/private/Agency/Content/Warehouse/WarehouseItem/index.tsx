import React from 'react';
import Button from '@mui/material/Button';

import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import Product from '~/pages/components/Product';
import { ProductDetailType, WarehouseType } from '~/utils/TypeGlobal';

const cx = ClassNames(style);

export interface Props {
    data: WarehouseType
}

function WarehouseItem({ data }: Props) {
    const [showMore, setShowMore] = React.useState(false);

    const toggleShow = () => {
        setShowMore(prev => !prev);
    }

    return (
        <div className={cx('container')}>
            <div className={cx('warehouse')}>
                <div className={cx('info')}>
                    <h2 className={cx('name')}>warehouse</h2>
                    <span className={cx('address')}>Address: {data.address}</span>
                    <span>Number of Products: {data.productdetails.length}</span>
                </div>

                <div className={cx('actions')}>
                    <Button variant="text" className={cx('go-inside', 'btn')} onClick={() => toggleShow()}>
                        {
                            showMore ? 'Show less' : 'Show more'
                        }
                    </Button>
                </div>
            </div>

            {
                <div className={cx('products')}>
                    {
                        showMore && (
                            data.productdetails.length > 0 ? (
                                data.productdetails.map((pd: ProductDetailType, index: number) => {
                                    return <Product key={`productdetail-${pd.id}-index-${index}`} data={pd.product} />
                                })
                            )
                                :
                                <div className={cx('no-product-in-warehouse')}>No products in warehouse</div>
                        )
                    }
                </div>
            }
        </div>
    );
}

export default WarehouseItem;