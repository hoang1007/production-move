import Sidebar from './Sidebar';
import Content from './Content';
import HeaderLayout from '~/pages/components/HeaderLayout';

import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
const cx = ClassNames(style);

function Agency() {
    return (
        <>
            <Sidebar />
            <div className="main">
                <HeaderLayout />
                <Content />
            </div>
        </>
    );
}

export default Agency;