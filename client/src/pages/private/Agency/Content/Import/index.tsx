import React, { useEffect } from 'react';
import { Button} from '@mui/material';
import { toast } from 'react-toastify';

import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
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
import Box from '@mui/material/Box';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import InputLabel from '@mui/material/InputLabel';
import OutlinedInput from '@mui/material/OutlinedInput';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';

import { useAxios, useAuth } from '~/hooks';
import { ProductType, WarehouseType } from '~/utils/TypeGlobal';
import api from '~/config/api';
// import { pendingProductsSelector } from '~/utils/selector';

const cx = ClassNames(style);

const columns: GridColDef[] = [
    { field: 'brand', headerName: 'Brand', flex: 1 },
    { field: 'phone', headerName: 'Phone', flex: 1 },
    { field: 'storage', headerName: 'Storage' , flex: 1},
    { field: 'color', headerName: 'Color' , flex: 1},
    { field: 'cost', headerName: 'Cost', flex: 1 },
    {
        field: 'actions',
        headerName: '',
        flex: 1,
        renderCell: (params) => {
            const onClick = (event: React.MouseEvent) => {
                event.stopPropagation();
            }
            return <Button className={cx('btn-see-detail')} onClick={(e) => onClick(e)}>See detail</Button>
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

interface Rows {
    id: number,
    brand: string,
    phone: string,
    storage: string,
    color: string,
    cost: string,
}

function Import() {
    const [auth] = useAuth()
    const axios = useAxios()
    const [loading, setLoading] = React.useState(false);
    const [dataRows, setDataRows] = React.useState<Readonly<Rows[]>>([]);
    const [warehouses, setListWarehouses] = React.useState<WarehouseType[]>([])
    const [pendingProducts, setPendingProducts] = React.useState<ProductType[]>([]);
    const [selectedProduct, setSelectedProduct] = React.useState<GridRowId[]>([]);
    const [selectedWarehouse, setSelectedWarehouse] = React.useState<number>(-1);
    const [open, setOpen] = React.useState(false);

    React.useEffect(() => {
        axios.get(api.agency.pendingProducts, {
            params: {
                agencyId: auth?.user.id
            }
        })
            .then(response => {
                if (response.status === 200) {
                    setListWarehouses(response.data.warehouses);
                    setPendingProducts(response.data.pendingProducts);
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

    React.useEffect(() => {
        const _dataRows: Rows[] = [];
        for (const product of pendingProducts) {
                _dataRows.push({
                    id: product.id,
                    brand: product.productline.brand,
                    phone: product.productline.phone,
                    storage: '100GB',
                    color: 'Black',
                    cost: '$2000'
                })
        }
        setDataRows(_dataRows)
    }, [warehouses])

    const postSelectedProducts = () => {
        axios.post(`${api.agency.importProducts}?agencyId=${auth?.user.id}&warehouseId=${selectedWarehouse}`, {
            productIds: selectedProduct
        }).then(response => {
            if (response.status === 200) {
                toast.success(response.data.message);
                setOpen(false)
                setDataRows(prev => prev.filter(p => !selectedProduct.includes(p.id)))
            }
        }).catch(error => {
            console.log(error)
            // setLoading(false)
            toast.error(error.message)
        })
    }

    const onSelectionModelChange = (rows: GridSelectionModel) => {
        setSelectedProduct(rows);
    }

    const handleChange = (event: SelectChangeEvent<typeof selectedWarehouse>) => {
        setSelectedWarehouse(Number(event.target.value));
    };

    const handleClickOpen = () => {
        if (selectedProduct.length === 0) {
            toast.error("You must choose at least one product!");
            return;
        }
        setOpen(true);
    };

    const handleClose = (event: React.SyntheticEvent<unknown>, reason?: string) => {
        if (reason !== 'backdropClick') {
            setOpen(false);
        }
    };

    const handleImport = () => {
        if (selectedWarehouse < 0) {
            toast.error("You must choose Warehouse to store selected products!");
            return;
        }
        
        postSelectedProducts()
    }

    

    return (
        <div className={cx('container')}>
            <div style={{ width: '100%' }} className={cx('table')}>
                <DataGrid
                    className={cx('data')}
                    columns={columns}
                    rows={dataRows}
                    components={{
                        Toolbar: CustomToolbar,
                    }}
                    checkboxSelection
                    onSelectionModelChange={onSelectionModelChange}
                />
            </div>

            <div className={cx('import-btn')}>
                <Button onClick={handleClickOpen}>Import</Button>
                <Dialog disableEscapeKeyDown open={open} onClose={handleClose}>
                    <DialogTitle>Choose Warehouse to store</DialogTitle>
                    <DialogContent>
                        <Box component="form" sx={{ display: 'flex', flexWrap: 'wrap' }}>
                            <FormControl sx={{ m: 1, minWidth: 120 }}>
                                <InputLabel htmlFor="demo-dialog-native">Warehouse</InputLabel>
                                <Select
                                    native
                                    value={selectedWarehouse}
                                    onChange={handleChange}
                                    input={<OutlinedInput label="Age" id="demo-dialog-native" />}
                                >
                                    <option aria-label="None" value="-1" />
                                    {
                                        warehouses.map((w: WarehouseType, index: number) =>
                                            <option key={`option-warehouse-${w.id}-index-${index}`} value={`${w.id}`}>
                                                Address: {w.address}
                                            </option>
                                        )
                                    }
                                </Select>
                            </FormControl>
                        </Box>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleClose}>Cancel</Button>
                        <Button onClick={handleImport}>Start importing</Button>
                    </DialogActions>
                </Dialog>
            </div>
        </div>
    );
}

export default Import;