import { Container, FormControl, InputLabel, MenuItem, Select, ToggleButton, ToggleButtonGroup } from "@mui/material";
import { useEffect, useState } from "react";
import { Bar, BarChart, Line, LineChart, Tooltip, XAxis, YAxis } from "recharts";
import api from "~/config/api";
import { useAuth } from "~/hooks";
import usePrivateAxios from "~/hooks/useAxios";
import { ProductStage } from "~/utils/selector";
import { ProductType } from "~/utils/TypeGlobal";

export default function Dashboard() {
    const [auth] = useAuth();
    const { get } = usePrivateAxios();
    const [products, setProducts] = useState<ProductType[]>([]);
    const [alignment, setAlignment] = useState("month");
    const [stage, setStage] = useState(ProductStage.NEW_PRODUCTION);

    useEffect(() => {
        get(api.factory.allCreatedProducts, { params: { id: auth?.user.id } }).then(res => {
            setProducts(res.data);
        }).catch(err => {
            console.log(err);
        });
    }, [])

    const getIntervalId = (date: Date) => {
        if (typeof date === "string") {
            date = new Date(date);
        }

        if (alignment === "month") {
            return date.getMonth() + 1;
        } else if (alignment === "quarter") {
            return Math.ceil((date.getMonth() + 1) / 3);
        } else if (alignment === "year") {
            return date.getFullYear();
        } else {
            throw new Error("Invalid alignment");
        }
    }

    const getIntervalLabel = (id: number | string) => {
        if (alignment === "month") {
            return `Tháng ${id}`;
        } else if (alignment === "quarter") {
            return `Quý ${id}`;
        } else if (alignment === "year") {
            return `Năm ${id}`;
        } else {
            throw new Error("Invalid alignment");
        }
    }

    const groupByInterval = (products: ProductType[]) => {
        var group: Record<number, ProductType[]> = {};

        products.forEach(product => {
            let intervalId = getIntervalId(product.productDetails[product.productDetails.length - 1].startAt);

            if (group[intervalId]) {
                group[intervalId].push(product);
            } else {
                group[intervalId] = [product];
            }
        });

        var ret = Object.entries(group).map(([key, value]) => {
            return {
                name: getIntervalLabel(key),
                value: value
            }
        });

        return ret;
    }
    
    const groupByStage = (products: ProductType[]) => {
        var group: Record<string, ProductType[]> = {};

        products.forEach(product => {
            let stage = product.productDetails[product.productDetails.length - 1].stage;
            if (group[stage]) {
                group[stage].push(product);
            } else {
                group[stage] = [product];
            }
        });

        return Object.entries(group).map(([key, value]) => {
            return {
                name: key,
                value: value
            }
        })
    }

    const renderStageBarChart = () => {
        var gr = groupByStage(products).find(item => item.name === stage)?.value || [];
        var data = groupByInterval(gr).map(item => {
            return {
                name: item.name,
                value: item.value.length
            }
        });

        return (
            <BarChart width={730} height={250} data={data}>
                <XAxis dataKey="name" />
                <YAxis />
                <Tooltip />
                <Bar dataKey="value" fill="#8884d8" />
            </BarChart>
        )
    }

    const renderSoldLineChart = () => {
        var sold = products.filter(product => {
            product.productDetails.forEach(detail => {
                if (detail.stage === ProductStage.SOLD) {
                    return true;
                }
            });
        });

        var data = groupByInterval(sold).map(item => {
            return {
                name: item.name,
                value: item.value.length
            }
        });

        return (
            <LineChart width={730} height={250} data={data}>
                <XAxis dataKey="name" />
                <YAxis />
                <Tooltip />
                <Line type="monotone" dataKey="value" stroke="#8884d8" />
            </LineChart>
        );
    }

    const renderToggleInterval = () => {
        return (
            <ToggleButtonGroup
                color="primary"
                value={alignment}
                exclusive
                onChange={(e, value) => setAlignment(value)}
                aria-label="Platform"
                sx={{ marginRight: "20px" }}
            >
                <ToggleButton value="month">Tháng</ToggleButton>
                <ToggleButton value="quarter">Quý</ToggleButton>
                <ToggleButton value="year">Năm</ToggleButton>
            </ToggleButtonGroup>
        )
    }

    const renderSelectStage = () => (
        <FormControl>
            <InputLabel id="demo-simple-select-label">Trạng thái</InputLabel>
            <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                value={stage}
                label="Age"
                onChange={ev => setStage(ev.target.value as string)}
            >
                {Object.entries(ProductStage).map(([key, value], index) => {
                    return <MenuItem key={`menu-item${key}-index-${index}`} value={value}>{value}</MenuItem>
                })
                }
            </Select>
        </FormControl>
    );

    return (
        <Container>
            <Container>
                {renderToggleInterval()}
            </Container>
            <Container sx={{ display: "flex", flexDirection: "column", alignItems: "center", marginBottom: "5rem" }}>
                {renderSelectStage()}
                {renderStageBarChart()}
                <p>Thống kê số lượng sản phẩm đang có trạng thái {stage}</p>
            </Container>
            <Container sx={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
                {renderSoldLineChart()}
                <p>Thống kê số lượng sản phẩm bán ra</p>
            </Container>
        </Container>
    );
}