import { Outlet } from 'react-router-dom';

import './style.scss'
import SidebarLayout from '~/pages/components/SidebarLayout'
import HeaderLayout from '~/pages/components/HeaderLayout';

interface Props {
    Sidebar: {
        title: 'BIGCORP' | 'AGENCY' | 'WARRANTY' | 'MANUFACTURING BASE',
        element: React.ReactNode,
    },
}

const Layout = ({Sidebar}: Props) => {
    return (
        <div className={'layout'}>
            <SidebarLayout title={Sidebar.title}>
                {Sidebar.element}
            </SidebarLayout>
            <div className="main">
                <HeaderLayout />
                <Outlet/>
            </div>
        </div>
    )
}

export default Layout;