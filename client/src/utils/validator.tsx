const DEFAULT_LENGTH_PASSWORD = 8;

const res = (msg: string, error: boolean) => {
    return {
        message: msg,
        error: error
    }
}

export const validatePassword = (password: string) => {
    password = password.trim();

    if (password.length === 0) {
        return res('Password must not be empty!', true);
    }

    if (password.length < DEFAULT_LENGTH_PASSWORD) {
        return res(`password more than ${DEFAULT_LENGTH_PASSWORD} characters`, true)
    }

    return res('', false)
}

export const validateUsername = (username: string) => {
    username = username.trim()
    if (username.length === 0) {
        return res('User name must not be empty!', true);
    }
    return res('', false)
}

export const validateEmail = (email: string) => {
    email = email.trim()
    if (email.length === 0) {
        return res('Email không được để trống!', true);
    }
    if (!email.includes('@')) {
        return res('Email không hợp lệ!', true);
    }
    return res('', false)
}

export const validatePhoneNumber = (phoneNumber: string) => {
    phoneNumber = phoneNumber.trim()
    if (phoneNumber.length === 0) {
        return res('Số điện thoại không được để trống!', true);
    }
    if (phoneNumber.length < 10 || phoneNumber.length > 11) {
        return res('Số điện thoại bao gồm 10 đến 11 chữ số!', true);
    }
    if (!/^\d+$/.test(phoneNumber)) {
        return res('Số điện thoại chỉ bao gồm các chữ số!', true);
    }
    return res('', false)
}