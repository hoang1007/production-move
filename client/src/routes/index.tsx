import React from 'react';
import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom'
import routes from '~/config/routes';

import RoleBaseRouting from './RoleBaseRouting';
import ProtectedRoutes from '~/middleware/ProtectedRoutes';
import Layout from '~/pages/Layout';
import ContentLayout from '~/pages/components/ContentLayout';
import NotFound from '~/pages/public/NotFound';

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
                <Route element={<ProtectedRoutes />}>
                    {
                        Object.keys(routes.private).map((key: string, index: number) => {
                            return (
                                <Route key={`private-${index}`} element={<RoleBaseRouting roles={routes.private[key].roles} />}>
                                        <Route element={<Layout Sidebar={{ element: routes.private[key].Sidebar, title: routes.private[key].title }} />}>
                                            {
                                                routes.private[key].Content.map((content: { title: string, path: string, element: React.ReactNode }, index: number) => (
                                                    <Route key={`private-content-${key}-${index}`} element={<ContentLayout title={content.title}/>}>
                                                        <Route path={content.path} element={content.element} />
                                                    </Route>
                                                ))
                                            }
                                        </Route>
                                </Route>
                            )
                        })
                    }
                </Route>

                <Route path="/*" element={<NotFound />} />

            </Routes>
        </BrowserRouter>
    )
}

export default RoutesApp;