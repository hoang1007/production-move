import { Autocomplete, Button, CircularProgress, Container, Dialog, DialogActions, DialogContent, DialogTitle, FormControl, TextField } from "@mui/material";
import { Box } from "@mui/system";
import React, { useEffect } from "react";
import InputSlider from "~/components/InputSlider";
import api from "~/config/api";
import usePrivateAxios from "~/hooks/useAxios";
import { AgencyType, BatchProductType, ProductType } from "~/utils/TypeGlobal";

export interface ExportProductsModal {
    open: boolean;
    setOpen: (open: boolean) => void;
    onClose?: (event: React.SyntheticEvent<unknown>, reason: "backdropClick" | "escapeKeyDown") => void;
    onConfirm: (batches: BatchProductType[], agency: AgencyType) => void;
    batches: BatchProductType[];
}

const ExportProductsModal: React.FC<ExportProductsModal> = ({ open, setOpen, onClose, batches, onConfirm }) => {
    console.log(batches, 'batches');
    const [quantities, setQuantities] = React.useState<number[]>(
        batches.map(batch => { return 1; })
    );
    const [agencies, setAgencies] = React.useState<AgencyType[]>([]);
    const [selectedAgency, setSelectedAgency] = React.useState<AgencyType | null>(null);
    const [loading, setLoading] = React.useState(true);
    const { get } = usePrivateAxios();

    useEffect(() => {
        setLoading(true);

        get(api.agency.allAgencies).then(res => {
            setAgencies(res.data);
            setLoading(false);
        })
    }, []);

    const setQuantity = (index: number, value: number) => {
        console.log('set quat', index, value);
        setQuantities(quantities.map((quantity, i) => {
            if (i === index) {
                return value;
            } else {
                return quantity;
            }
        }));
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Xuất sản phẩm</DialogTitle>
            {loading ? <CircularProgress /> : <DialogContent>
                <Box component="form" sx={{ display: "flex", flex: 1 }}>
                    <Container sx={{ flexDirection: "column" }}>
                        {batches.map((batch, index) => {
                            return (
                                <InputSlider
                                    key={batch.id}
                                    title={batch.name}
                                    value={quantities[index]}
                                    setValue={value => setQuantity(index, value)}
                                    min={1}
                                    max={batch.quantity}
                                />
                            );
                        })}
                    </Container>
                    <Autocomplete
                        value={selectedAgency}
                        onChange={(event, newValue) => setSelectedAgency(newValue)}
                        loading={loading}
                        options={agencies}
                        getOptionLabel={(option) => option.name}
                        renderInput={(params) => <TextField {...params} sx={{ minWidth: 250 }} label="Đại lý phân phối" />}
                    />
                </Box>
            </DialogContent>}
            <DialogActions>
                <Button onClick={() => setOpen(false)}>Hủy</Button>
                <Button onClick={() => {
                    setOpen(false);

                    if (selectedAgency) {
                        const exportBatches = batches.map((batch, index) => {
                            return {
                                ...batch,
                                quantity: quantities[index],
                            };
                        });
                        onConfirm(exportBatches, selectedAgency);
                    }
                }}>Xuất</Button>
            </DialogActions>
        </Dialog>
    );
}

export default ExportProductsModal;