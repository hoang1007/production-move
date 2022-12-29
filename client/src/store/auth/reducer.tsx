import { Action } from '~/utils/TypeGlobal';

export interface Auth {
    isLoggedIn: boolean,
    user: {
        username: string,
        role: string,
        id: number,
    },
    accessToken: string
}

export const initialState: Auth = {
    isLoggedIn: false,
    user: {
        username: '',
        role: '',
        id: -1
    },
    accessToken: ''
}

const reducer = (state = initialState, actions: Action) => {
    const { type, payload } = actions;

    switch (type) {
        case 'LOGIN':
            return {
                ...state,
                isLoggedIn: true,
                user: {
                    username: payload.username,
                    role: payload.role,
                    id: payload.id,
                },
                accessToken: payload.accessToken
            }
        
        case 'LOGOUT':
            return {
                ...initialState
            }
    
        default:
            return state;
    }
}

export default reducer;