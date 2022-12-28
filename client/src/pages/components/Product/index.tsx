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
    data: ProductType
}

function Product({ data }: Props) {
    const [showDetail, setShowDetail] = React.useState<boolean>(false)

    const toggleShow = () => {
        setShowDetail(prev => !prev);
    }

    return (
        <div className={cx('container')}>
            <div className={cx('preview')}>
                <div className={cx('basic-info')}>
                    <Image className={cx('img')} src={data.productline.pictureUrlSmall} />
                    <div><span>Name</span>: {data.productline.phone}</div>
                    -
                    <div><span>Branch</span>: {data.productline.brand}</div>
                </div>

                <div className={cx('actions')}>
                    <Button className={cx('btn')} variant="text" onClick={() => toggleShow()}>
                        {
                            showDetail ? 'Show less' : 'Show more'
                        }
                    </Button>
                </div>

            </div>
            <div className={cx('detail-product')}>
                {
                    showDetail &&
                    <Box sx={{ width: '100%' }}>
                        <Grid container rowSpacing={1} columnSpacing={{ xs: 1, sm: 2, md: 3 }}>
                            {
                                Object.keys(data.productline).map((key: string, index: number) => {
                                
                                    return (
                                        <Grid
                                            key={`pd-${key}-index-${index}`}
                                            item xs={3}
                                        >
                                            <span className={cx('field')}>{key}: </span>{data.productline[key] || '_'}
                                        </Grid>)
                                })
                            }
                        </Grid>
                    </Box>
                }
            </div>
        </div>
    );
}

export default Product;