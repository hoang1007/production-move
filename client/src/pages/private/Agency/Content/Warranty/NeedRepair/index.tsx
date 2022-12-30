import { Autocomplete, Box, Button, Container, Dialog, DialogActions, DialogContent, DialogTitle, TextField } from "@mui/material";
import { DataGrid, GridColDef } from "@mui/x-data-grid";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { PlusIcon } from "~/components/Icon";
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
    }
]

export default function NeedRepair() {
    const [auth] = useAuth();
    const { get, post } = usePrivateAxios();
    const [customers, setCustomers] = useState<CustomerType[]>([]);
    const [openModal, setOpenModal] = useState<boolean>(false);
    const [selectedCustomerId, setSelectedCustomerId] = useState<number | null>(null);
    const [selectedProduct, setSelectedProduct] = useState<ProductType | null>(null);
    const [description, setDescription] = useState<string>("");

    useEffect(() => {
        get(api.agency.getOrders, { params: { agencyId: auth?.user?.id } }).then(res => {
            setCustomers(res.data);
        })
    }, []);

    const annouceNeedRepair = () => {
        post(api.agency.receiveNeedRepairs, { productIds: [selectedProduct!.id], reasons: [description] }).then(res => {
            setOpenModal(false);
            setSelectedCustomerId(null);
            setSelectedProduct(null);
            setDescription("");

            toast.success("Thành công");
        })
    }

    const getTableData = () => {
        const data: any[] = [];

        customers.forEach((customer, ci) => {
            customer.orders.forEach((order, oi) => {
                const product = order.product;
                const detail = product.productDetails[product.productDetails.length - 1];
                if (detail.stage === ProductStage.NEED_REPAIR) {
                    data.push({
                        id: `${ci} ${oi}`,
                        name: customer.fullname,
                        phoneNumber: customer.phoneNumber,
                        product: product.productline.phone,
                        description: detail.description,
                    });
                }
            });
        });

        return data;
    };

    const renderWarrantyModal = () => {
        const selectedCustomer = customers.filter(customer => customer.id === selectedCustomerId);
        const options: ProductType[] = [];

        if (selectedCustomer.length == 1) {
            selectedCustomer[0].orders.forEach(order => {
                const product = order.product;
                const detail = product.productDetails[product.productDetails.length - 1];
                if (detail.stage === ProductStage.SOLD) {
                    options.push(product);
                }
            });
        }

        return (
            <Dialog open={openModal} onClose={() => setOpenModal(false)}>
                <DialogTitle>Sản phẩm cần sửa chữa</DialogTitle>
                <DialogContent>
                    <Box component="form" sx={{ display: "flex", flex: 1, flexDirection: "column", alignItems: "center" }}>
                        <TextField
                            value={selectedCustomerId}
                            onChange={(e) => {
                                if (e.target.value === "") {
                                    setSelectedCustomerId(null);
                                } else {
                                    const value = parseInt(e.target.value);
                                    if (isNaN(value)) {
                                        setSelectedCustomerId(null);
                                    } else {
                                        setSelectedCustomerId(value);
                                    }
                                }
                            }}
                            sx={{ minWidth: 250, marginBottom: "1rem" }}
                            label="Mã khách hàng"
                        />
                        {selectedCustomerId ? <p style={{ fontSize: 11, margin: "10px" }}>Khách hàng {selectedCustomer[0].fullname} cần bảo hành</p> : null}
                        <Autocomplete
                            value={selectedProduct}
                            onChange={(event, newValue) => setSelectedProduct(newValue)}
                            options={options}
                            getOptionLabel={(option) => option.productline.phone + " #" + option.id}
                            renderInput={(params) => <TextField {...params} sx={{ minWidth: 250, marginBottom: "1rem" }} label="Sản phẩm" />}
                        />
                        <TextField
                            value={description}
                            onChange={(e) => setDescription(e.target.value)}
                            sx={{ minWidth: 250, marginBottom: "1rem" }}
                            label="Mô tả lỗi"
                        />
                    </Box>
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => setOpenModal(false)}>Hủy</Button>
                    <Button onClick={annouceNeedRepair}>Xác nhận</Button>
                </DialogActions>
            </Dialog>
        );
    }

    return (
        <Container sx={{ height: "100%" }}>
            <Button
                endIcon={<PlusIcon />}
                sx={{ marginBottom: "1rem" }}
                onClick={() => setOpenModal(true)}
            >Thêm</Button>
            <DataGrid
                rows={getTableData()}
                columns={columns}
                pageSize={5}
                rowsPerPageOptions={[5]}
            />
            {renderWarrantyModal()}
        </Container>
    );
}