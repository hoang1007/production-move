import { useEffect, useState } from "react";
import { ProductDetailType } from "~/utils/TypeGlobal";
import usePrivateAxios from "~/hooks/useAxios";
import api from "~/config/api";
import { DataGrid, GridColDef, GridRowId, GridValueFormatterParams, GridValueGetterParams } from "@mui/x-data-grid";
import { Button, Container } from "@mui/material";
import ClassNames from "~/utils/classNames";
import style from "./style.module.scss";

const cx = ClassNames(style);

function ReceiveProducts() {
    const { get, post } = usePrivateAxios();
    const [productDetails, setProductDetails] = useState<ProductDetailType[]>([]);
    const [selectedProductIds, setSelectedProductIds] = useState<number[]>([]);
    const [loading, setLoading] = useState<boolean>(true);

    const columns: GridColDef[] = [
        {
            field: 'name',
            headerName: 'Tên sản phẩm',
            flex: 1,
            valueGetter: (params: GridValueGetterParams<ProductDetailType>) => params.row.product.name,
        },
        {
            field: 'description',
            headerName: 'Mô tả',
            width: 500,
        },
        {
            field: 'startAt',
            headerName: 'Ngày báo lỗi',
            width: 200,
            type: 'dateTime',
            valueFormatter: ({ value }: GridValueFormatterParams<Date>) => {
                return value ? value.toLocaleDateString("vi", {
                    year: "numeric", month: "long", day: "numeric", hour: "2-digit", hour12: true
                }) : '';
            },
            valueGetter: ({ value }) => value && new Date(value),
        }
    ];

    useEffect(() => {
        if (loading) {
            get(api.warrantyCenter.needRepairProducts).then(res => {
                setProductDetails(res.data);
                setLoading(false);
            })
        }
    }, [loading]);

    const handleReceiveProducts = () => {
        post(api.warrantyCenter.receiveProducts, { "productIds": selectedProductIds }).then(res => {
            console.log(res);
            setSelectedProductIds([]);
            setLoading(true);
        });
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
                rows={productDetails}
                columns={columns}
                pageSize={10}
                rowsPerPageOptions={[10]}
                checkboxSelection
                onSelectionModelChange={(selectionModel, details) => {
                    const selectedRowIds = selectionModel.map((id: GridRowId) => Number(id));

                    const selectedDetails = productDetails.filter((batch, index) => selectedRowIds.includes(index));
                    setSelectedProductIds(selectedDetails.map(detail => detail.product.id));
                }}
            />
        </Container>
    );
}

export default ReceiveProducts;