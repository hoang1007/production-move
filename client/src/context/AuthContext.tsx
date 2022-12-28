import React from "react";

import { reducer, initialState } from '~/store/auth';
import { Auth } from "~/store/auth/reducer";
import { Action } from "~/utils/TypeGlobal";

const authContext = React.createContext<[Auth?, React.Dispatch<Action>?]>([]);

export const AuthProvider = ({children}: {children:React.ReactNode }) => {
    const [authReducer, authDispatch] = React.useReducer(reducer, initialState)

    return (
        <authContext.Provider value={[authReducer, authDispatch]} >
            {children}
        </authContext.Provider>
    )
}

export default authContext;