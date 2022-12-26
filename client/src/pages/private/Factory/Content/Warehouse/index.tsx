import { Button } from "@mui/material";
import { Container } from "@mui/system";
import { DataGrid, GridColDef } from "@mui/x-data-grid";
import { useParams } from "react-router-dom";
import { PlusIcon } from "~/components/Icon";
import ClassNames from "~/utils/classNames";
import style from "./style.module.scss";

const cx = ClassNames(style);

function Warehouse() {
    const { id } = useParams<{ id: string }>();

    const columns: GridColDef[] = [
        { field: 'name', headerName: 'Tên sản phẩm', width: 200 },
        { field: 'quantity', headerName: 'Số lượng', width: 200 },
        { field: 'importDate', headerName: 'Ngày nhập kho', width: 200, type: 'date' },
    ]

    const data = [
        { id: 1, name: 'Iphone 14', quantity: 100, importDate: '2021-10-10' },
        { id: 2, name: 'Iphone 15', quantity: 100, importDate: '2021-10-10' },
        { id: 3, name: 'Iphone 16', quantity: 100, importDate: '2021-10-10' },
        { id: 4, name: 'Iphone 17', quantity: 100, importDate: '2021-10-10' },
    ]

    return (
        <Container id={cx('container')}>
            <Button className={cx('button')} endIcon={<PlusIcon className={cx('icon')} />}>Thêm</Button>
            <DataGrid
                className={cx('table')}
                rows={data}
                columns={columns}
                pageSize={5}
                rowsPerPageOptions={[5]}
                checkboxSelection
            />
        </Container>
    )
}

export default Warehouse;