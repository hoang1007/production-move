import React from 'react';
import { Box, Grid } from '@mui/material';
import {keys} from 'ts-transformer-keys';

import { ProductType, ProductLineType } from '~/utils/TypeGlobal';
import Image from '~/components/Image';
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import { Button } from '@mui/material';
const cx = ClassNames(style);

interface Props {
    data: ProductType,
    action?: Function
}

function Product({ data, action }: Props) {
    const [showDetail, setShowDetail] = React.useState<boolean>(false)

    const toggleShow = () => {
        setShowDetail(prev => !prev);
    }

    return (
        <div className={cx('container')}>
            <div className={cx('preview')}>
                <div className={cx('basic-info')}>
                    <Image className={cx('img')} src={data.productline.pictureUrlSmall} />
                    <div><span>Tên</span>: {data.productline.phone}</div>
                    -
                    <div><span>Hãng</span>: {data.productline.brand}</div>
                </div>

                <div className={cx('actions')}>
                    <Button
                        className={cx('btn')} variant="text"
                        onClick={() => {
                            toggleShow()
                            if(action) action()
                        }}>
                        Chi tiết
                    </Button>
                </div>

            </div>
        </div>
    );
}

export default Product;