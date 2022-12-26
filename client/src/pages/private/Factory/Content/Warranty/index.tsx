import { DataGrid, GridColDef, GridRenderCellParams } from '@mui/x-data-grid';
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import { Button, Container } from '@mui/material';

const cx = ClassNames(style);

function Warranty() {
    const detailButton = (params: GridRenderCellParams) => (
        <a href="ok">Chi tiết</a>
    );

    const columns: GridColDef[] = [
        { field: "name", headerName: "Tên thiết bị", width: 200 },
        { field: "description", headerName: "Mô tả lỗi", width: 200 },
        { field: "date", headerName: "Ngày gửi", width: 200, type: "date" },
        { field: "warranty", headerName: "Trung tâm Bảo hành", width: 400 },
        { field: "", headerName: "", renderCell: detailButton, hideSortIcons: true, sortable: false, hideable: false }
    ]

    const data = [
        { id: 1, name: "Máy in", description: "Không in được", date: Date.now(), warranty: "Trung tâm Bảo hành 1" },
        { id: 2, name: "Máy in", description: "Không in được", date: "2021-10-10", warranty: "Trung tâm Bảo hành 1" },
        { id: 3, name: "Máy in", description: "Không in được", date: "2021-10-10", warranty: "Trung tâm Bảo hành 1" },
        { id: 4, name: "Máy in", description: "Không in được", date: "2021-10-10", warranty: "Trung tâm Bảo hành 1" },
    ]

    return (
        <Container id={cx('container')}>
            <Button className={cx('button')}>Nhận sản phẩm</Button>
            <DataGrid
                className={cx('table')}
                rows={data}
                columns={columns}
                pageSize={5}
                rowsPerPageOptions={[5]}
                checkboxSelection
            />
        </Container>
    );
}

export default Warranty;