import React from 'react';

import { ProductLineType } from '~/utils/TypeGlobal';
import api from '~/config/api'
import { useAxios } from '~/hooks';
import Loading from '~/components/Loading';
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import { toast } from 'react-toastify';
import { Box, Button, Container } from '@mui/material';
import { BackIcon } from '~/components/Icon';
import {
    DataGrid, GridColDef,
    GridToolbarContainer,
    GridToolbarColumnsButton,
    GridToolbarFilterButton,
    GridToolbarExport,
    GridToolbarDensitySelector,
    GridSelectionModel,
    GridRowId
} from '@mui/x-data-grid';
import Image from '~/components/Image';
import ProductLineItem from '~/pages/components/ProductLine';
const cx = ClassNames(style);

const columns: GridColDef[] = [
    { field: 'index', headerName: 'Index', flex: 1, maxWidth: 100 },
    {
        field: 'Picture',
        headerName: 'picture',
        flex: 1,
        renderCell: (params) => {
            return <Image className={cx('picture-pdl')} src={params.row.picture} />
        },
        maxWidth: 100
    },
    {
        field: 'brand', headerName: 'brand', flex: 1,
        maxWidth: 100

    },
    { field: 'phone', headerName: 'phone' , flex: 1},
    { field: 'notes', headerName: 'notes' , flex: 1},
    {
        field: 'actions',
        headerName: '',
        flex: 1,
        renderCell: (params) => {
            const onClick = (event: React.MouseEvent) => {
                event.stopPropagation();
            }
            return <Button className={cx('btn-see-detail')} onClick={params.row.actions.onClick} >
                See detail
            </Button>
        }
    }
];

function CustomToolbar() {
  return (
    <GridToolbarContainer className={cx('GridToolbarContainer')}>
      <GridToolbarColumnsButton />
      <GridToolbarFilterButton />
      <GridToolbarDensitySelector />
      <GridToolbarExport />
    </GridToolbarContainer>
  );
}

function ProductLine() {
    const [currentProductLine, setCurrentProductLine] = React.useState<ProductLineType | null>(null);
    const [listProductLines, setListProductLines] = React.useState<ProductLineType[]>([]);
    const axios = useAxios();
    const [loading, setLoading] = React.useState(true);

    React.useEffect(() => {
        axios.get(api.productline.productlines)
            .then(res => {
                console.log(res)
                setListProductLines(res.data)
            }).catch(err => {
                console.log(err)
                toast.error('Something went wrong.')
            })
    }, [])

    const showDetail = (id: number) => {
        const selectedProductline = listProductLines.find(p => p.id === id);
        if (selectedProductline) {
            setCurrentProductLine(selectedProductline);
        } else {
            toast.error('Cannot see detail. Something went wrong!')
        }
    }

    return (
        <div className={cx('container')}>
            {
                currentProductLine === null ?
                    <DataGrid
                        className={cx('data')}
                        columns={columns}
                        disableSelectionOnClick
                        rows={listProductLines.map((i, index) => ({
                            index: index,
                            id: i.id,
                            picture: i.pictureUrlSmall,
                            brand: i.brand,
                            phone: i.phone,
                            notes: i.notes,
                            actions: {onClick: () => showDetail(i.id)}
                        }))}
                        rowsPerPageOptions={[50]}
                        // pageSize={10}

                        components={{
                            Toolbar: CustomToolbar,
                        }}
                        getRowClassName={() => cx('row')}
                    />
                    :
                    <div className={cx('detail')}>
                        <div className={cx('actions')}>
                            <Button className={cx('back')}
                                onClick={() => setCurrentProductLine(null)}
                            > <BackIcon /> </Button>
                        </div>
                        <div className={cx('pdl-detail')}>
                            <Container sx={{ display: "flex" , flexDirection: "row", justifyContent: "space-between" }}>
                                <Box
                                    component="img"
                                    src={currentProductLine.pictureUrlSmall}
                                    sx={{ width: "fit-content", height: "fit-content" }}
                                />
                                <ProductLineItem product={currentProductLine} />
                            </Container>
                        </div>
                    </div>
            }
        </div>
    );
}

export default ProductLine;