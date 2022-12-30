import { Grid, Paper } from "@mui/material";
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import { useNavigate, useParams } from "react-router-dom";
import api from "~/config/api";
import usePrivateAxios from "~/hooks/useAxios";
import { WarehouseType } from "~/utils/TypeGlobal";
import { useEffect, useState } from "react";
import { factoryRoutes } from "~/config/routes";
const cx = ClassNames(style);


function WarehouseList() {
    const navigate = useNavigate();
    const axios = usePrivateAxios();

    const [warehouses, setWarehouses] = useState<WarehouseType[]>([]);

    useEffect(() => {
        axios.get(api.factory.allWarehouses, {params: {id: 1}}).then(res => {
            console.log(res.data);
            setWarehouses(res.data);
        });
    }, []);

    return (
        <Grid container spacing={3} id={cx("container")}>
            {warehouses.map((warehouse, index) => (
                <Grid item key={index} md="auto">
                    <Paper onClick={() => navigate(`${factoryRoutes.warehouseList}/${warehouse.id}`)} elevation={3} className={cx("warehouse-card")}>
                        <h3>Kho #{warehouse.id}</h3>
                        <p>{warehouse.address}</p>
                    </Paper>
                </Grid>
            ))}
        </Grid>
    );
}

export default WarehouseList;