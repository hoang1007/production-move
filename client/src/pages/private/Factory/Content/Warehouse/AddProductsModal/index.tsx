import styled from "@emotion/styled";
import { Autocomplete, Button, CircularProgress, Dialog, DialogActions, DialogContent, DialogTitle, FormControl, TextField } from "@mui/material";
import { Box, darken, lighten } from "@mui/system";
import React, { useEffect } from "react";
import api from "~/config/api";
import usePrivateAxios from "~/hooks/useAxios";
import { BatchProductType, ProductLineType } from "~/utils/TypeGlobal";
import classNames from "~/utils/classNames";
import style from "./style.module.scss";

const cx = classNames(style);

export interface AddProductsModal {
    onConfirm: (batch: BatchProductType) => void;
    onClose?: (event: React.SyntheticEvent<unknown>, reason: "backdropClick" | "escapeKeyDown") => void;
    open: boolean;
    setOpen: (open: boolean) => void;
}

const GroupHeader = styled('div')({
    position: 'sticky',
    top: '-8px',
    padding: '4px 10px'
});

const GroupItems = styled('ul')({
    padding: 0,
});

const AddProductsModal: React.FC<AddProductsModal> = ({ onConfirm, open, setOpen, onClose }) => {
    const { get } = usePrivateAxios();
    const [options, setOptions] = React.useState<ProductLineType[]>([]);
    const [loading, setLoading] = React.useState(true);
    const [selected, setSelected] = React.useState<ProductLineType | null>(null);
    const [quantity, setQuantity] = React.useState<number | null>(1);
    const [isQuantityInputError, setIsQuantityInputError] = React.useState(false);

    useEffect(() => {
        get(api.productline.productlines).then((res) => {
            setOptions(res.data);
            setLoading(false);
        });
    }, []);

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Nhập sản phẩm mới</DialogTitle>
            <DialogContent>
                <Box component="form" sx={{ display: 'flex', flexWrap: 'wrap' }}>
                    <FormControl sx={{ m: 1, minWidth: 250, width: "fit-content" }}>
                        <Autocomplete
                            value={selected}
                            onChange={(event, newValue) => setSelected(newValue)}
                            loading={loading}
                            options={options.sort((a, b) => -b.brand.localeCompare(a.brand))}
                            getOptionLabel={(option) => option.phone}
                            renderInput={(params) => (<TextField {...params} label="Sản phẩm" className={cx("textfield")} />)}
                            groupBy={(option) => option.brand}
                            renderGroup={(params) => (
                                <li>
                                    <GroupHeader>{params.group}</GroupHeader>
                                    <GroupItems>{params.children}</GroupItems>
                                </li>
                            )}
                        />
                        <TextField
                            className={cx("textfield")}
                            error={isQuantityInputError}
                            id="filled-error-helper-text"
                            label="Số lượng"
                            type="number"
                            value={quantity ?? ''}
                            onChange={(e) => {
                                if (e.target.value === '') {
                                    setIsQuantityInputError(true);
                                    setQuantity(null);
                                }

                                const value = parseInt(e.target.value);
                                if (value > 0) {
                                    setQuantity(value);
                                    setIsQuantityInputError(false);
                                } else {
                                    setIsQuantityInputError(true);
                                }
                            }}
                        />
                    </FormControl>
                </Box>
            </DialogContent>
            <DialogActions>
                <Button onClick={() => setOpen(false)}>Hủy</Button>
                <Button onClick={() => {
                    setOpen(false);
                    if (selected) {
                        onConfirm({
                            id: selected.id,
                            quantity: quantity!,
                            name: selected.phone,
                            importDate: new Date()
                        });
                    }
                }}>Thêm</Button>
            </DialogActions>
        </Dialog>
    );
}

export default AddProductsModal;