import { Grid, Paper } from "@mui/material";
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import { useNavigate, useParams } from "react-router-dom";
import api from "~/config/api";
const cx = ClassNames(style);


function WarehouseList() {
    const navigate = useNavigate();

    const warehouses = [
        { id: "1", name: "Warehouse 1", address: "ngõ 181, Xuân Thủy, Cầy Giấy, Hà Nội" },
        { id: "2", name: "Warehouse 2", address: "1234 Main St" },
        { id: "3", name: "Warehouse 3", address: "1234 Main St" },
        { id: "4", name: "Warehouse 4", address: "1234 Main St" },
    ]

    return (
        <Grid container spacing={3} id={cx("container")}>
            {warehouses.map((warehouse, index) => (
                <Grid item key={index} md="auto">
                    <Paper onClick={() => navigate(`${api.factory.warehouseList}/${warehouse.id}`)} elevation={3} className={cx("warehouse-card")}>
                        <h3>{warehouse.name}</h3>
                        <p>{warehouse.address}</p>
                    </Paper>
                </Grid>
            ))}
        </Grid>
    );
}

export default WarehouseList;