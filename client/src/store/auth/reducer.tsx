import { Action } from '~/utils/TypeGlobal';

export interface Auth {
    isLoggedIn: boolean,
    user: {
        username: string,
        role: string
    },
    accessToken: string
}

export const initialState: Auth = {
    isLoggedIn: false,
    user: {
        username: '',
        role: ''
    },
    accessToken: ''
}

const reducer = (state = initialState, actions: Action) => {
    const { type, payload } = actions;

    switch (type) {
        case '':
            
            break;
    
        default:
            return state;
    }
}

export default reducer;