import { Container, Paper, Table, TableCell, TableRow, TableRowProps } from "@mui/material";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { ProductLineType } from "~/utils/TypeGlobal";
import usePrivateAxios from "~/hooks/useAxios";
import ClassNames from "~/utils/classNames";
import style from "./style.module.scss";
import ProductLine from "~/pages/components/ProductLine";
import api from "~/config/api";

const cx = ClassNames(style);

function ReturnedProduct() {
    const { id } = useParams<{ id: string }>();
    const axios = usePrivateAxios();
    const [product, setProduct] = useState<ProductLineType>();
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        axios.get(api.productline.productlineInfo, { params: { id: id } }).then(res => {
            setLoading(false);
            setProduct(res.data);
        });
    }, []);

    return (
        loading ? <div></div> : <ProductLine className={cx("productline")} product={product!} />
    );
}

export default ReturnedProduct;