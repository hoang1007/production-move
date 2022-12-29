import { Button, CircularProgress, Snackbar } from "@mui/material";
import { Container } from "@mui/system";
import { DataGrid, GridColDef, GridRowId, GridValueFormatterParams } from "@mui/x-data-grid";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { DeliveryIcon, PlusIcon } from "~/components/Icon";
import usePrivateAxios from "~/hooks/useAxios";
import { ProductType, BatchProductType, AgencyType } from "~/utils/TypeGlobal";
import ClassNames from "~/utils/classNames";
import style from "./style.module.scss";
import api from "~/config/api";
import AddProductsModal from "./AddProductsModal";
import ExportProductsModal from "./ExportProductsModal";
import { generalRoutes } from "~/config/routes";

const cx = ClassNames(style);

function Warehouse() {
    const { id } = useParams<{ id: string }>();
    const { get, post } = usePrivateAxios();
    const [loading, setLoading] = useState(true);
    const [products, setProducts] = useState<ProductType[]>([]);
    const [addModalOpen, setAddModalOpen] = useState(false);

    const [exportModalOpen, setExportModalOpen] = useState(false);
    const [selectedBatches, setSelectedBatches] = useState<BatchProductType[]>([]);

    const [notifySnackbarOpen, setNotifySnackbarOpen] = useState(false);
    const [notifyMessage, setNotifyMessage] = useState('');

    const columns: GridColDef[] = [
        {
            field: 'name',
            headerName: 'Tên sản phẩm',
            // width: 200,
            flex: 1
        },
        {
            field: 'quantity',
            headerName: 'Số lượng',
            width: 200
        },
        {
            field: 'importDate',
            headerName: 'Ngày nhập kho',
            width: 200, type: 'dateTime',
            valueFormatter: ({ value }: GridValueFormatterParams<Date>) => {
                return value ? value.toLocaleDateString("vi", {
                    year: "numeric", month: "long", day: "numeric", hour: "2-digit", hour12: true
                }) : '';
            },
            valueGetter: ({ value }) => value && new Date(value),
        },
        {
            field: '',
            renderCell: (params) => {

                return (<a href={generalRoutes.product.replace(":id", params.id!.toString())}>Chi tiết</a>)
            },
            hideSortIcons: true,
            sortable: false,
            hideable: false,
        }
    ]

    useEffect(() => {
        setLoading(true);
        console.log("Fetching...");
        get(api.warehouse.newProducts, { params: { id: id } }).then(res => {
            setProducts(res.data);
            setLoading(false);
            console.log(res.data);
        })
    }, [addModalOpen, exportModalOpen]);

    const collateProducts = () => {
        const batches: BatchProductType[] = [];

        products.forEach(product => {
            const batch = batches.find(batch => batch.name === product.productline.phone && batch.importDate === product.productDetails[0].startAt);
            if (batch) {
                batch.quantity += 1;
            } else {
                batches.push({
                    id: product.productline.id,
                    name: product.productline.phone,
                    quantity: 1,
                    importDate: product.productDetails[0].startAt,
                });
            }
        });

        return batches;
    }

    const handleCloseAddModal = (event: React.SyntheticEvent<unknown>, reason: "backdropClick" | "escapeKeyDown") => {
        if (reason === 'backdropClick') {
            setAddModalOpen(false);
        }
    }

    const handleCloseExportModal = (event: React.SyntheticEvent<unknown>, reason: "backdropClick" | "escapeKeyDown") => {
        if (reason === 'backdropClick') {
            setExportModalOpen(false);
            setSelectedBatches([]);
        }
    }

    const handleConfirmAddModal = (batch: BatchProductType) => {
        post(api.factory.importProducts, {
            productlineId: batch.id,
            quantity: batch.quantity,
            warehouseId: parseInt(id!)
        }).then(res => {
            setNotifyMessage('Thêm sản phẩm thành công!');
            setNotifySnackbarOpen(true);
        }).catch(err => {
            console.log(err);

            setNotifyMessage('Thêm sản phẩm thất bại!');
            setNotifySnackbarOpen(true);
        })
    };

    const exportToAgency = (batches: BatchProductType[], agency: AgencyType) => {
        const exportProductIds: number[] = [];

        batches.forEach(batch => {
            let count = 0;
            for (var product of products) {
                if (product.productline.phone === batch.name) {
                    exportProductIds.push(product.id);
                    count += 1;
                }

                if (count === batch.quantity) {
                    break;
                }
            }
        });

        post(api.factory.exportProducts, {
            productIds: exportProductIds,
            agencyId: agency.id,
        }).then(res => {
            setNotifyMessage('Xuất sản phẩm thành công!');
            setNotifySnackbarOpen(true);
        }).catch(err => {
            console.log(err);

            setNotifyMessage('Xuất sản phẩm thất bại!');
            setNotifySnackbarOpen(true);
        })
    };

    return (
        loading ? <CircularProgress /> : <Container id={cx('container')}>
            <h1>Kho #{id}</h1>
            <Container>
                <Button
                    className={cx('button')}
                    onClick={() => setAddModalOpen(true)}
                    endIcon={<PlusIcon className={cx('icon')} />}
                >Thêm</Button>
                <Button
                    className={cx('button')}
                    onClick={() => {
                        if (selectedBatches.length > 0) {
                            setExportModalOpen(true);
                        }
                    }}
                    endIcon={<DeliveryIcon className={cx('icon')} />}
                >Xuất</Button>
            </Container>
            <DataGrid
                loading={loading}
                className={cx('table')}
                rows={collateProducts()}
                columns={columns}
                pageSize={5}
                rowsPerPageOptions={[5]}
                checkboxSelection
                onSelectionModelChange={(selectionModel, details) => {
                    const selectedRowIds = selectionModel.map((id: GridRowId) => Number(id));

                    const batches = collateProducts().filter((batch, index) => selectedRowIds.includes(index));
                    setSelectedBatches(batches);
                }}
            />
            <AddProductsModal
                open={addModalOpen}
                setOpen={setAddModalOpen}
                onConfirm={handleConfirmAddModal}
                onClose={handleCloseAddModal}
            />
            <ExportProductsModal
                open={exportModalOpen}
                setOpen={setExportModalOpen}
                batches={selectedBatches}
                onConfirm={exportToAgency}
                onClose={handleCloseExportModal}
            />
            <Snackbar
                open={notifySnackbarOpen}
                autoHideDuration={4000}
                onClose={() => setNotifySnackbarOpen(false)}
                message={notifyMessage}
            />
        </Container>
    )
}

export default Warehouse;