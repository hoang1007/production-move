import React from "react";
import authContext from "~/context/AuthContext";
/**
 * Biến Global lưu trữ thông tin đăng nhập
 * return [auth, dispatch]
 * {@link ~/store/auth/reducer}
 */
const useAuth = () => {
    return React.useContext(authContext);
}

export default useAuth;