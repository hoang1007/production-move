import { DataGrid, GridColDef, GridRenderCellParams, GridRowId } from '@mui/x-data-grid';
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import { Button, Container } from '@mui/material';
import React, { useEffect } from 'react';
import { toast } from 'react-toastify';

const cx = ClassNames(style);

function Warranty() {
    const [selectedId, setSelectedId] = React.useState<number[]>([]);
    const [data, setData] = React.useState<any>([]);

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

    useEffect(() => {
        setData(
            [
                { id: 1, name: "Ericsson GA 318", description: "Hỏng bàn phím", date: "2022-12-29", warranty: "Trung tâm Bảo hành 1" },
                { id: 2, name: "Apple iPad Wi-Fi", description: "Không bắt được WiFi", date: "2022-10-10", warranty: "Trung tâm Bảo hành 1" },
                { id: 3, name: "Apple iPhone 5", description: "Vỡ màn hình", date: "2022-12-29", warranty: "Trung tâm Bảo hành 1" },
                { id: 4, name: "Apple iPhone 5", description: "Vỡ màn hình", date: "2022-12-29", warranty: "Trung tâm Bảo hành 1" },
            ]
        )
    }, [])

    return (
        <Container id={cx('container')}>
            <Button className={cx('button')} onClick={() => {
                setData(data.filter((item: any) => !selectedId.includes(item.id)));
                setSelectedId([]);
                toast.success("Nhận sản phẩm thành công!");
            }}>Nhận sản phẩm</Button>
            <DataGrid
                className={cx('table')}
                rows={data}
                columns={columns}
                pageSize={5}
                rowsPerPageOptions={[5]}
                checkboxSelection
                onSelectionModelChange={(newSelection) => {
                    const selectedRowIds = newSelection.map((id: GridRowId) => Number(id));

                    setSelectedId(selectedRowIds);
                }}
            />
        </Container>
    );
}

export default Warranty;