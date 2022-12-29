import { useEffect, useState } from "react";
import { ProductDetailType } from "~/utils/TypeGlobal";
import usePrivateAxios from "~/hooks/useAxios";

function ReceiveProducts() {
    const { get } = usePrivateAxios();
    const [productDetails, setProductDetails] = useState<ProductDetailType[]>([]);
    const [loading, setLoading] = useState<boolean>(false);

    useEffect(() => {
        get()
    }, []);
    
    return (
        <div>OK</div>
    );
}

export default ReceiveProducts;