import { Button, Container } from "@mui/material";
import { DataGrid, GridColDef } from "@mui/x-data-grid";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
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

export default function Repaired() {
    const { get, post } = usePrivateAxios();
    const [auth] = useAuth();
    const [customers, setCustomers] = useState<CustomerType[]>([]);

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

                if (detail.stage === ProductStage.REPAIRED && detail.endAt) {
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

    const returnToCustomer = () => {
        const prodIds: number[] = [];

        customers.forEach((customer, ci) => {
            customer.orders.forEach((order, oi) => {
                const product = order.product;
                const detail = product.productDetails[product.productDetails.length - 1];

                if (detail.stage === ProductStage.REPAIRED && detail.endAt) {
                    prodIds.push(product.id);
                }
            });
        });

        post(api.agency.returnToCustomer, { productIds: prodIds }).then(res => {
            toast.success("Đã trả lại thiết bị cho khách hàng");
        }).catch(er => {
            toast.error("Có lỗi xảy ra");
        });
    }

    return (
        <Container>
            <Button onClick={returnToCustomer}>Trả lại cho khách hàng</Button>
            <DataGrid
                rows={getTableData()}
                columns={columns}
                pageSize={5}
                rowsPerPageOptions={[5]}
            />
        </Container>
    )
}