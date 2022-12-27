import { Action } from '~/utils/TypeGlobal';

export interface Auth {
    isLoggedIn: boolean,
    user: {
        username: string,
        role: string,
        agencyId: number,
    },
    accessToken: string
}

export const initialState: Auth = {
    isLoggedIn: false,
    user: {
        username: 'bi',
        role: 'AGENCY',
        agencyId: 1
    },
    accessToken: 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiaSIsImV4cCI6MTY3MjExMDYxNiwiaWF0IjoxNjcyMDI0MjE2fQ.G3qfFtr9-AmhUTNn-trhD1VoFjy8fLveVCOuCc9T8Hx4jTij3q9aOYAOKFOOllzxuvJvGxwObXeoPtUilMwYVw'
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