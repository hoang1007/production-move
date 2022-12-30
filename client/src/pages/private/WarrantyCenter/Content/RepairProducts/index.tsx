import { GridColDef, GridValueFormatterParams, GridValueGetterParams } from "@mui/x-data-grid";
import { useEffect, useState } from "react";
import api from "~/config/api";
import { useAuth } from "~/hooks";
import usePrivateAxios from "~/hooks/useAxios";
import { ProductDetailType } from "~/utils/TypeGlobal";

function RepairProducts() {
    const [auth] = useAuth();
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
            get(api.warrantyCenter.repairingProducts, { params: {
                "warrantyCenterId": auth?.user.id
            } }).then(res => {
                setProductDetails(res.data);
                setLoading(false);
            })
        }
    }, [loading])

    return (
        <div>OK</div>
    );
}

export default RepairProducts;