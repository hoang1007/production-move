import { Button, Container } from "@mui/material";
import { DataGrid, GridColDef, GridRowId, GridValueFormatterParams, GridValueGetterParams } from "@mui/x-data-grid";
import { useEffect, useState } from "react";
import api from "~/config/api";
import { useAuth } from "~/hooks";
import usePrivateAxios from "~/hooks/useAxios";
import { ProductDetailType } from "~/utils/TypeGlobal";
import ClassNames from "~/utils/classNames";
import style from "./style.module.scss";
import { toast } from "react-toastify";

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
        flex: 1
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

function RepairProducts() {
    const [auth] = useAuth();
    const { get, post } = usePrivateAxios();
    const [productDetails, setProductDetails] = useState<ProductDetailType[]>([]);
    const [selectedProductIds, setSelectedProductIds] = useState<number[]>([]);
    const [loading, setLoading] = useState<boolean>(true);

    useEffect(() => {
        if (loading) {
            get(api.warrantyCenter.repairingProducts, {
                params: {
                    "warrantyCenterId": auth?.user.id
                }
            }).then(res => {
                setProductDetails(res.data);
                setLoading(false);
            })
        }
    }, [loading])

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

    const handleRepairedProducts = () => {
        post(api.warrantyCenter.returnToAgency, {
            "productIds": selectedProductIds
        }).then(res => {
            toast.success("Đã hoàn thành sửa chữa sản phẩm");
            setLoading(true);
        }).catch(err => {
            toast.error("Đã xảy ra lỗi");
        })
    }

    const handleUnRepairedProducts = () => {
        post(api.warrantyCenter.returnToFactory, {
            "productIds": selectedProductIds
        }).then(res => {
            toast.success("Đã trả sản phẩm về cơ sở sản xuất");
            setLoading(true);
        }).catch(err => {
            toast.error("Đã xảy ra lỗi");
        })
    }

    return (
        <Container id={cx("container")}>
            <Container>
                <Button
                    // variant="contained"
                    className={cx("button")}
                    onClick={handleRepairedProducts}
                >
                    Hoàn thành sửa chữa
                </Button>
                <Button
                    // variant="contained"
                    className={cx("button")}
                    onClick={handleUnRepairedProducts}
                >
                    Trả về cở sở sản xuất
                </Button>
            </Container>

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

export default RepairProducts;