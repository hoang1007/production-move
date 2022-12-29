import { TableContainer, Table, TableCell, Container, TableRow } from "@mui/material";
import { ProductLineType } from "~/utils/TypeGlobal";
import ClassNames from "~/utils/classNames";
import style from "./style.module.scss";

const cx = ClassNames(style);

function VerticalTableRow({ title, children }: { title: string, children: React.ReactNode }) {
    return (
        <TableRow sx={{ height: 4}}>
            <TableCell variant="head" sx={{ width: "20rem", fontSize: 12 }}>{title}</TableCell>
            <TableCell sx={{ fontSize: 12}}>{children}</TableCell>
        </TableRow>
    );
}

interface ProductLineProps {
    product: ProductLineType;
    className?: string;
}

const ProductLine: React.FC<ProductLineProps> = ({ product, className }) => {
    const renderScreenTable = () => (
        <Container id={cx("container")}>
            <p>Màn hình</p>
            <Table className={cx("table")}>
                <VerticalTableRow title="Kích thước màn hình">{product.displaySize}</VerticalTableRow>
                <VerticalTableRow title="Công nghệ màn hình">{product.displayType}</VerticalTableRow>
                <VerticalTableRow title="Độ phân giải màn hình">{product.displayResolution}</VerticalTableRow>
            </Table>
        </Container>
    );

    const renderMainCameraTable = () => (
        <Container id={cx("container")}>
            <p>Camera sau</p>
            <Table className={cx("table")}>
                <VerticalTableRow title="Camera sau">{product.mainCameraDual}</VerticalTableRow>
                <VerticalTableRow title="Quay phim">{product.mainCameraVideo}</VerticalTableRow>
                <VerticalTableRow title="Tính năng camera">{product.mainCameraFeatures}</VerticalTableRow>
            </Table>
        </Container>
    );

    const renderFrontCameraTable = () => (
        <Container id={cx("container")}>
            <p>Camera trước</p>
            <Table className={cx("table")}>
                <VerticalTableRow title="Camera trước">{product.selfieCameraDual}</VerticalTableRow>
                <VerticalTableRow title="Quay phim">{product.selfieCameraVideo}</VerticalTableRow>
            </Table>
        </Container>
    );

    const renderChipsetGPUTable = () => (
        <Container id={cx("container")}>
            <p>Vi xử lý và đồ họa</p>
            <Table className={cx("table")} >
                <VerticalTableRow title="Vi xử lý">{product.platformChipset}</VerticalTableRow>
                <VerticalTableRow title="CPU">{product.platformCpu}</VerticalTableRow>
                <VerticalTableRow title="GPU">{product.platformGpu}</VerticalTableRow>
            </Table>
        </Container>
    );

    const renderConnectivityTable = () => (
        <Container id={cx("container")}>
            <p>Giao tiếp & Kết nối</p>
            <Table className={cx("table")}>
                <VerticalTableRow title="Thẻ SIM">{product.bodySim}</VerticalTableRow>
                <VerticalTableRow title="Hệ điều hành">{product.platformOs}</VerticalTableRow>
                <VerticalTableRow title="Wi-Fi">{product.commsWlan}</VerticalTableRow>
                <VerticalTableRow title="Bluetooth">{product.commsBluetooth}</VerticalTableRow>
                <VerticalTableRow title="GPS">{product.commsGps}</VerticalTableRow>
            </Table>
        </Container>
    );

    return (
        <TableContainer className={[className, cx("container")].join(' ')}>
            <Table>
                <div>{product.phone}</div>
                {renderScreenTable()}
                {renderMainCameraTable()}
                {renderFrontCameraTable()}
                {renderChipsetGPUTable()}
                {renderConnectivityTable()}
            </Table>
        </TableContainer>
    );
}

export default ProductLine;