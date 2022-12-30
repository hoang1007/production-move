import { Button, Container } from "@mui/material";
import { DataGrid, GridColDef, GridRowId } from "@mui/x-data-grid";
import { useEffect, useState } from "react";
import api from "~/config/api";
import { useAuth } from "~/hooks";
import usePrivateAxios from "~/hooks/useAxios";
import { ProductType, WarehouseType } from "~/utils/TypeGlobal";

export interface ProductListProps {
    onConfirm: (product: ProductType[]) => void;
}


const columns: GridColDef[] = [
    {
        field: 'name',
        headerName: 'Tên sản phẩm',
        flex: 1,
        type: 'string',
    },
    {
        field: 'price',
        headerName: 'Giá',
        type: 'number',
        valueGetter: ({ value }) => {
            return value.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
        },
    }
]

const ProductList: React.FC<ProductListProps> = ({ onConfirm }) => {
    const [auth] = useAuth();
    const { get } = usePrivateAxios();
    const [products, setProducts] = useState<ProductType[]>([]);
    const [selectedProducts, setSelectedProducts] = useState<ProductType[]>([]);
    const [loading, setLoading] = useState<boolean>(true);

    useEffect(() => {
        if (loading) {
            get(api.agency.allWarehouses, { params: { agencyId: auth?.user?.id } }).then(res => {
                const products: ProductType[] = res.data.map((warehouse: WarehouseType) => warehouse.products).flat();
                setProducts(products);
                setLoading(false);
            })
        }
    }, [loading]);

    const getTableData = () => {
        return products.map((product, index) => ({
            id: product.id,
            name: product.productline.phone,
            price: 23e6,
        }));
    }

    return (
        <Container>
            <Button onClick={() => {
                onConfirm(selectedProducts);
                setLoading(true);
            }}>Đặt hàng</Button>
            <DataGrid
                loading={loading}
                rows={getTableData()}
                columns={columns}
                pageSize={5}
                rowsPerPageOptions={[5]}
                checkboxSelection
                onSelectionModelChange={(selectionModel, details) => {
                    const selectedRowIds = selectionModel.map((id: GridRowId) => Number(id));

                    const selectedProducts = products.filter(product => selectedRowIds.includes(product.id));
                    setSelectedProducts(selectedProducts);
                }}
            />
        </Container>
    );
}

export default ProductList;