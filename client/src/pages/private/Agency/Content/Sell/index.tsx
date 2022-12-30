import { Box, Button, Container, Dialog, DialogActions, DialogContent, DialogTitle, FormControl, IconButton, TextField } from "@mui/material";
import { useState } from "react";
import { CustomerType, ProductType } from "~/utils/TypeGlobal";
import { SearchIcon } from "~/components/Icon";
import ClassNames from "~/utils/classNames";
import style from "./style.module.scss";
import usePrivateAxios from "~/hooks/useAxios";
import { validateEmail, validatePhoneNumber } from "~/utils/validator";
import api from "~/config/api";
import { toast } from "react-toastify";
import ProductList from "./ProductList";

const cx = ClassNames(style);

export default function Sell() {
    const { get, post } = usePrivateAxios();
    const [customer, setCustomer] = useState<CustomerType | null>(null);
    const [searchCustomerInput, setSearchCustomerInput] = useState<string>("");
    const [customerInputError, setCustomerInputError] = useState<string>("");
    const [showSaveCustomerSuggestion, setShowSaveCustomerSuggestion] = useState<boolean>(false);
    const [showSaveCustomerModal, setShowSaveCustomerModal] = useState<boolean>(false);

    const [customerNameInput, setCustomerNameInput] = useState<string>("");
    const [customerEmailInput, setCustomerEmailInput] = useState<string>("");
    const [customerPhoneInput, setCustomerPhoneInput] = useState<string>("");
    const [customerEmailError, setCustomerEmailError] = useState<string>("");
    const [customerPhoneError, setCustomerPhoneError] = useState<string>("");

    const findCustomer = () => {
        if (searchCustomerInput === "") {
            setCustomer(null);
            setCustomerInputError("Không được để trống");
            return;
        }

        const emailRes = validateEmail(searchCustomerInput);

        if (!emailRes.error) {
            get(api.customer.getByEmail, { params: { email: searchCustomerInput } }).then(res => {
                setCustomer(res.data);
            }).catch(err => {
                toast.error("Không tìm thấy khách hàng");
                setCustomer(null);
                setShowSaveCustomerSuggestion(true);
            });
        } else {
            const phoneRes = validatePhoneNumber(searchCustomerInput);
            if (!phoneRes.error) {
                get(api.customer.getByPhone, { params: { phone: searchCustomerInput } }).then(res => {
                    setCustomer(res.data);
                }).then(err => {
                    toast.error("Không tìm thấy khách hàng");
                    setCustomer(null);
                    setShowSaveCustomerSuggestion(true);
                });
            } else {
                setCustomer(null);
                setCustomerInputError("Email hoặc số điện thoại không hợp lệ");
            }
        }
    }

    const saveCustomerInfo = () => {
        if (customerNameInput === "") {
            toast.error("Không được để trống họ và tên");
            return;
        }

        if (customerEmailInput === "" && customerPhoneInput === "") {
            toast.error("Email và số điện thoại không được đồng thời để trống");
            return;
        }

        if (customerEmailInput !== "") {
            const emailRes = validateEmail(customerEmailInput);
            if (emailRes.error) {
                setCustomerEmailError("Email không hợp lệ");
                return;
            } else {
                setCustomerEmailError("");
            }
        }

        if (customerPhoneInput !== "") {
            const phoneRes = validatePhoneNumber(customerPhoneInput);
            if (phoneRes.error) {
                setCustomerPhoneError("Số điện thoại không hợp lệ");
                return;
            } else {
                setCustomerPhoneError("");
            }
        }

        const data = {
            fullName: customerNameInput,
            email: customerEmailInput.length === 0 ? null : customerEmailInput,
            phoneNumber: customerPhoneInput.length === 0 ? null : customerPhoneInput,
        }

        post(api.customer.create, data).then(res => {
            toast.success("Tạo hồ sơ thành công");
            setCustomer(res.data);
            setShowSaveCustomerModal(false);
        }).catch(err => {
            toast.error("Tạo hồ sơ thất bại");
        });
    }

    const createOrders = (products: ProductType[]) => {
        if (customer === null) {
            toast.error("Không tìm thấy khách hàng");
            return;
        }

        const data = {
            customerId: customer.id,
            productIds: products.map(p => p.id),
        }

        post(api.customer.order, data).then(res => {
            toast.success("Tạo đơn hàng thành công");
        }).catch(err => {
            toast.error("Tạo đơn hàng thất bại");
        });
    }

    const renderCustomerInfo = () => (
        <Container>
            <Container sx={{
                display: "flex",
                alignItems: "center",
            }}>
                <TextField
                    className={cx("textfield")}
                    label="Email hoặc số điện thoại"
                    variant="outlined"
                    value={searchCustomerInput}
                    onChange={(e) => {
                        let newval = e.target.value;
                        setSearchCustomerInput(newval);

                        if (newval.length === 0) {
                            setCustomerInputError("");
                            return;
                        }

                        const emailRes = validateEmail(newval);
                        const phoneRes = validatePhoneNumber(newval);

                        if (emailRes.error && phoneRes.error) {
                            setCustomerInputError("Email hoặc số điện thoại không hợp lệ");
                            return;
                        } else {
                            setCustomerInputError("");
                        }
                    }}
                    error={customerInputError !== ""}
                    helperText={customerInputError}
                />
                <IconButton
                    className={cx("search-btn")}
                    onClick={findCustomer}
                >
                    <SearchIcon />
                </IconButton>
            </Container>
            {customer ?
                <Container>
                    <p>Mã khách hàng: {customer.id}</p>
                    <p>Họ và tên: {customer.fullname}</p>
                    {customer.phoneNumber ? <p>Số điện thoại: {customer.phoneNumber}</p> : null}
                    {customer.email ? <p>Email: {customer.email}</p> : null}
                </Container>
                : null
            }
            {showSaveCustomerSuggestion ?
                <Container>
                    <p>Không tìm thấy khách hàng</p>
                    <Button variant="text" onClick={() => {
                        setShowSaveCustomerModal(true);
                        setShowSaveCustomerSuggestion(false);
                        setSearchCustomerInput("");
                    }}>Tạo hồ sơ</Button>
                </Container>
                : null}
        </Container>
    );

    const renderSaveCustomerInfoModal = () => (
        <Dialog open={showSaveCustomerModal} onClose={() => setShowSaveCustomerModal(false)}>
            <DialogTitle>Thông tin khách hàng</DialogTitle>
            <DialogContent>
                <Box component="form" sx={{ display: 'flex', flexWrap: 'wrap' }}>
                    <FormControl sx={{ m: 1, minWidth: 250, width: "fit-content" }}>
                        <TextField
                            className={cx("textfield")}
                            value={customerNameInput}
                            onChange={(e) => setCustomerNameInput(e.target.value)}
                            label="Họ và tên"
                            variant="outlined"
                            required />
                        <TextField
                            className={cx("textfield")}
                            value={customerPhoneInput}
                            onChange={(e) => {
                                setCustomerPhoneInput(e.target.value);

                                if (e.target.value.length === 0) {
                                    setCustomerPhoneError("");
                                    return;
                                }

                                const res = validatePhoneNumber(e.target.value);
                                if (res.error) {
                                    setCustomerPhoneError(res.message);
                                } else {
                                    setCustomerPhoneError("");
                                }
                            }}
                            label="Số điện thoại"
                            variant="outlined"
                            error={customerPhoneError !== ""}
                            helperText={customerPhoneError}
                        />
                        <TextField
                            className={cx("textfield")}
                            value={customerEmailInput}
                            onChange={(e) => {
                                setCustomerEmailInput(e.target.value);

                                if (e.target.value.length === 0) {
                                    setCustomerEmailError("");
                                    return;
                                }

                                const res = validateEmail(e.target.value);
                                if (res.error) {
                                    setCustomerEmailError(res.message);
                                } else {
                                    setCustomerEmailError("");
                                }
                            }}
                            label="Email"
                            variant="outlined"
                            error={customerEmailError !== ""}
                            helperText={customerEmailError}
                        />
                    </FormControl>
                </Box>
            </DialogContent>
            <DialogActions>
                <Button onClick={() => setShowSaveCustomerModal(false)}>Hủy</Button>
                <Button onClick={saveCustomerInfo}>Lưu</Button>
            </DialogActions>
        </Dialog>
    );

    return (
        <Container sx={{display: "flex", flexDirection: "row", flex: 1}}>
            {renderCustomerInfo()}
            {showSaveCustomerModal ? renderSaveCustomerInfoModal() : null}
            {customer ? <ProductList onConfirm={createOrders} /> : null}
        </Container>
    );
}