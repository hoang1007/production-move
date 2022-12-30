import { Button, Container } from "@mui/material";
import { DataGrid, GridColDef, GridRowId } from "@mui/x-data-grid";
import { useEffect, useState } from "react";
import api from "~/config/api";
import { useAuth } from "~/hooks";
import usePrivateAxios from "~/hooks/useAxios";
import { ProductStage } from "~/utils/selector";
import { CustomerType, ProductType } from "~/utils/TypeGlobal";


const columns: GridColDef[] = [
    {
        field: 'name',
        headerName: 'Tên khách hàng',
        flex: 1,
    },
    {
        field: 'phoneNumber',
        headerName: 'Số điện thoại',
        flex: 1,
    },
    {
        field: 'product',
        headerName: 'Tên thiết bị',
        flex: 1,
    },
    {
        field: 'description',
        headerName: 'Mô tả',
        width: 400,
    },
    {
        field: 'warranyCenter',
        headerName: 'Trung tâm bảo hành',
        flex: 1,
    }
]

export default function ReturnedToFactory() {
    const { get } = usePrivateAxios();
    const [auth] = useAuth();
    const [customers, setCustomers] = useState<CustomerType[]>([]);
    const [selectedRows, setSelectedRows] = useState<ProductType[]>([]);

    useEffect(() => {
        get(api.agency.getOrders, { params: { agencyId: auth?.user?.id } }).then(res => {
            setCustomers(res.data);
        })
    }, []);

    const getTableData = () => {
        const data: any[] = [];

        customers.forEach((customer, ci) => {
            customer.orders.forEach((order, oi) => {
                const product = order.product;
                const detail = product.productDetails[product.productDetails.length - 1];

                if (detail.stage === ProductStage.RETURNED_TO_FACTORY) {
                    data.push({
                        id: data.length,
                        name: customer.fullname,
                        phoneNumber: customer.phoneNumber,
                        product: product.productline.phone,
                        description: detail.description,
                        warranyCenter: detail.warrantyCenter.name,
                    });
                }
            });
        });

        return data;
    }

    return (
        <Container>
            <Button>
                Bàn giao sản phẩm mới</Button>
            <DataGrid
                rows={getTableData()}
                columns={columns}
                pageSize={5}
                rowsPerPageOptions={[5]}
                onSelectionModelChange={(selectionModel, details) => {
                    const selectedRowIds = selectionModel.map((id: GridRowId) => Number(id));


                }}
            />
        </Container>
    )
}