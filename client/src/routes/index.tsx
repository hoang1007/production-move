import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import routes from '~/config/routes';

import RoleBaseRouting from './RoleBaseRouting';
import ProtectedRoutes from '~/middleware/ProtectedRoutes';

const RoutesApp: React.FC = () => {
    return (
        <BrowserRouter>
            <Routes>
                    
                    {/* public routes */}
                    {Object.keys(routes.public).map((key, index) => {
                        const path = routes.public[key].path;
                        const element = routes.public[key].element
                        return (
                            <Route key={`public-${index}`} path={path} element={element} />
                        )
                    })}

                    {/* private routes */}
                    <Route path="/" element={<ProtectedRoutes />}>
                        {
                            Object.keys(routes.private).map((key, index) => {
                                const path = routes.private[key].path;
                                const element = routes.private[key].element
                                const roles = routes.private[key].roles;
                                const children = routes.private[key].children
                                return (
                                    <Route key={`private-${index}`} element={<RoleBaseRouting roles={roles} />}>
                                            <Route path={path} element={element} ></Route>
                                        {
                                            children?.map((c: { path: string, element: React.ReactNode }, index: number) => {
                                                return <Route key={`private-children-${index}`} path={path + c.path} element={c.element} />
                                            })
                                        }
                                    </Route>
                                )
                            })
                        }
                    </Route>

            </Routes>
        </BrowserRouter>
    )
};

export default RoutesApp;