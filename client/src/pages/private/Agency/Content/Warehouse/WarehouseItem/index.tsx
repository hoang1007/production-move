import React from 'react';
import Button from '@mui/material/Button';

import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import Product from '~/pages/components/Product';
import { ProductDetailType, WarehouseType } from '~/utils/TypeGlobal';

const cx = ClassNames(style);

export interface Props {
    data: WarehouseType,
    setCurrentProduct: Function
}

function WarehouseItem({ data, setCurrentProduct }: Props) {
    const [showMore, setShowMore] = React.useState(false);

    const toggleShow = () => {
        setShowMore(prev => !prev);
    }

    return (
        <div className={cx('container')}>
            <div className={cx('warehouse')}>
                <div className={cx('info')}>
                    <h2 className={cx('name')}>Kho</h2>
                    <span className={cx('address')}>Địa chỉ: {data.address}</span>
                    <span>Số lượng sản phẩm trong kho: {data.products.length}</span>
                </div>

                <div className={cx('actions')}>
                    <Button variant="text" className={cx('go-inside', 'btn')} onClick={() => toggleShow()} >
                        {
                            showMore ? 'Thu gọn' : 'Xem kho'
                        }
                    </Button>
                </div>
            </div>

            {
                <div className={cx('products')}>
                    {
                        showMore && (
                            data.products.length > 0 ? (
                                data.products.map((product, index: number) => {
                                    return <Product
                                        key={`productdetail-${product.id}-index-${index}`}
                                        data={product}
                                        action={() => setCurrentProduct(product)}
                                    />
                                })
                            )
                                :
                                <div className={cx('no-product-in-warehouse')}>Không có sản phẩm</div>
                        )
                    }
                </div>
            }
        </div>
    );
}

export default WarehouseItem;