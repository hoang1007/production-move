import { Container, Table, TableCell, TableRow, TableRowProps } from "@mui/material";
import { useEffect } from "react";
import { useParams } from "react-router-dom";
import usePrivateAxios from "~/hooks/useAxios";
import ClassNames from "~/utils/classNames";
import style from "./style.module.scss";

const cx = ClassNames(style);

function VerticalTableRow({ title, children }: { title: string, children: React.ReactNode }) {
    return (
        <TableRow sx={{height: 5}}>
            <TableCell variant="head" sx={{width: "20rem", fontSize: 12}}>{title}</TableCell>
            <TableCell sx={{fontSize: 12}}>{children}</TableCell>
        </TableRow>
    );
}

function ReturnedProduct() {
    const { id } = useParams<{ id: string }>();
    const axios = usePrivateAxios();

    useEffect(() => {
        axios.get(`api/productline_info?id=${id}`).then(res => {
            console.log(res.data);
        });
    })

    const renderScreenTable = () => (
        <Container id={cx("container")}>
            <p>Màn hình</p>
            <Table className={cx("table")}>
                <VerticalTableRow title="Kích thước màn hình">6.1 inches</VerticalTableRow>
                <VerticalTableRow title="Công nghệ màn hình" children="Super Retina XDR OLED" />
                <VerticalTableRow title="Độ phân giải màn hình" children="2532 x 1170 pixels" />
                <VerticalTableRow title="Tính năng màn hình" children="120Hz, Always-On display, HDR, True Tone, Haptic Touch, 2,000,000:1, 2000 nits" />
                <VerticalTableRow title="Tần số quét" children="120Hz" />
            </Table>
        </Container>
    );

    const renderMainCameraTable = () => (
        <Container id={cx("container")}>
            <p>Camera sau</p>
            <Table className={cx("table")}>
                <VerticalTableRow title="Camera sau">
                    <p>Camera chính: 48 MP, f/1.8, 24mm, OIS </p>
                    <p>Camera góc siêu rộng: 12 MP, f/2.2, 13mm, 120˚</p>
                    <p>Camera tele: 12 MP, f/2.8, 77mm, OIS, 3x optical zoom </p>
                    <p>Cảm biến độ sâu TOF 3D LiDAR </p>
                </VerticalTableRow>
                <VerticalTableRow title="Quay phim">4K@24/30/60fps, 1080p@30/60/120/240fps, HDR, stereo sound rec</VerticalTableRow>
                <VerticalTableRow title="Tính năng camera">Quay phim Cinematic, Chế độ ProRes, Quay phim Dolby Vision HDR</VerticalTableRow>
            </Table>
        </Container>
    );

    const renderFrontCameraTable = () => (
        <Container id={cx("container")}>
            <p>Camera trước</p>
            <Table className={cx("table")}>
                <VerticalTableRow title="Camera trước">12 MP, f/2.2, 23mm, 1/3.6", 1.4µm, Dual Pixel PDAF, OIS</VerticalTableRow>
                <VerticalTableRow title="Quay phim">4K@24/30/60fps, 1080p@30/60/120fps, HDR, stereo sound rec</VerticalTableRow>
            </Table>
        </Container>
    );

    const renderChipsetGPUTable = () => (
        <Container id={cx("container")}>
            <p>Vi xử lý và đồ họa</p>
            <Table className={cx("table")}>
                <VerticalTableRow title="Vi xử lý">Apple A14 Bionic 5nm</VerticalTableRow>
                <VerticalTableRow title="GPU">Apple GPU 5 nhân</VerticalTableRow>
            </Table>
        </Container>
    );

    const renderConnectivityTable = () => (
        <Container id={cx("container")}>
            <p>Giao tiếp & Kết nối</p>
            <Table className={cx("table")}>
                <VerticalTableRow title="Thẻ SIM">2 SIM (nano‑SIM và eSIM)</VerticalTableRow>
                <VerticalTableRow title="Hệ điều hành">iOS 16</VerticalTableRow>
                <VerticalTableRow title="Wi-Fi">Dual-band (2.4 GHz/5 GHz), Wi-Fi 802.11 a/b/g/n/ac/ax, Wi-Fi hotspot, Wi-Fi MIMO</VerticalTableRow>
                <VerticalTableRow title="Bluetooth">A2DP, LE, v5.3</VerticalTableRow>
                <VerticalTableRow title="GPS">BEIDOU, GALILEO, GLONASS, GPS, iBeacon, QZSS</VerticalTableRow>
            </Table>
        </Container>
    );

    return (
        <Container>
            {renderScreenTable()}
            {renderMainCameraTable()}
            {renderFrontCameraTable()}
            {renderChipsetGPUTable()}
            {renderConnectivityTable()}
        </Container>
    );
}

export default ReturnedProduct;