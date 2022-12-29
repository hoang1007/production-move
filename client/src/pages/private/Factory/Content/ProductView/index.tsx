import { Box, CircularProgress } from "@mui/material";
import { Container } from "@mui/system";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "~/config/api";
import usePrivateAxios from "~/hooks/useAxios";
import ProductLine from "~/pages/components/ProductLine";
import { ProductLineType } from "~/utils/TypeGlobal";
import ClassNames from "~/utils/classNames";
import style from './style.module.scss';

const cx = ClassNames(style);

export default function Product() {
    const { id } = useParams<{ id: string }>();
    const [productLine, setProductLine] = useState<ProductLineType | null>(null);
    const { get } = usePrivateAxios();

    useEffect(() => {
        get(api.productline.productlineInfo, { params: { id: id } }).then(res => {
            console.log(res.data);
            setProductLine(res.data);
        });
    }, []);

    return (
        productLine ?
            <Container sx={{ display: "flex" , flexDirection: "row", justifyContent: "space-between" }}>
                <Box
                    component="img"
                    src={productLine.pictureUrlSmall}
                    sx={{ width: "fit-content", height: "fit-content" }}
                />
                <ProductLine className={cx("table")} product={productLine!} />
            </Container> :
            <CircularProgress />
    );
};