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