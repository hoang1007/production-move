import { useEffect, useState } from "react";
import { ProductDetailType } from "~/utils/TypeGlobal";
import usePrivateAxios from "~/hooks/useAxios";
import api from "~/config/api";
import { DataGrid, GridColDef, GridRowId, GridValueFormatterParams, GridValueGetterParams } from "@mui/x-data-grid";
import { Button, Container } from "@mui/material";
import ClassNames from "~/utils/classNames";
import style from "./style.module.scss";
import { toast } from "react-toastify";
import { useAuth } from "~/hooks";

const cx = ClassNames(style);

const columns: GridColDef[] = [
    {
        field: 'name',
        headerName: 'Tên sản phẩm',
        flex: 1,
    },
    {
        field: 'description',
        headerName: 'Mô tả',
        flex: 1,
    },
    {
        field: 'startAt',
        headerName: 'Ngày báo lỗi',
        flex: 1,
        type: 'dateTime',
        valueFormatter: ({ value }: GridValueFormatterParams<Date>) => {
            return value ? value.toLocaleDateString("vi", {
                year: "numeric", month: "long", day: "numeric", hour: "2-digit", hour12: true
            }) : '';
        },
        valueGetter: ({ value }) => value && new Date(value),
    }
];

function ReceiveProducts() {
    const [auth] = useAuth();
    const { get, post } = usePrivateAxios();
    const [productDetails, setProductDetails] = useState<ProductDetailType[]>([]);
    const [selectedProductIds, setSelectedProductIds] = useState<number[]>([]);
    const [loading, setLoading] = useState<boolean>(true);

    useEffect(() => {
        if (loading) {
            get(api.warrantyCenter.needRepairProducts).then(res => {
                setProductDetails(res.data);
                setLoading(false);
                console.log(res.data);
            })
        }
    }, [loading]);

    const handleReceiveProducts = () => {
        const data = {
            productIds: selectedProductIds,
            warrantyCenterId: auth?.user.id
        }

        console.log(data);
        
        post(api.warrantyCenter.receiveProducts, data).then(res => {
                console.log(res);
                setSelectedProductIds([]);
                setLoading(true);
                toast.success("Nhận sản phẩm thành công");
            }).catch(err => {
                toast.error("Có lỗi xảy ra, vui lòng thử lại sau");
            });
    }

    const getTableData = () => {
        const data = productDetails.map((detail, index) => {
            return {
                id: detail.product.id,
                name: detail.product.productline.phone,
                description: detail.description,
                startAt: detail.startAt,
            }
        });

        return data;
    }

    return (
        <Container id={cx("container")}>
            <Button
                // variant="contained"
                className={cx("button")}
                onClick={handleReceiveProducts}
            >
                Nhận sản phẩm
            </Button>

            <DataGrid
                className={cx("table")}
                loading={loading}
                rows={getTableData()}
                columns={columns}
                pageSize={10}
                rowsPerPageOptions={[10]}
                checkboxSelection
                onSelectionModelChange={(selectionModel, details) => {
                    const selectedRowIds = selectionModel.map((id: GridRowId) => Number(id));
                    setSelectedProductIds(selectedRowIds);
                }}
            />
        </Container>
    );
}

export default ReceiveProducts;