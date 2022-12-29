

export const login = (payload: {
    username: string,
    role: string,
    id: number,
    accessToken: string
}) => {
    return {
        type: 'LOGIN',
        payload
    }
}

export const logout = () => {
    return {
        type: 'LOGOUT',
        payload: ''
    }
}