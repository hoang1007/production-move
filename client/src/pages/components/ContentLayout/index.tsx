import { Outlet } from 'react-router-dom';

import style from './style.module.scss';
import ClassNames from '~/utils/classNames';

const cx = ClassNames(style);

interface Props {
    title: string,  // cái title này lấy theo routes nhé. VD: /agency/import thì title là import
}

function Content({ title }: Props) {
    return ( 
        <div className="content-layout">
            <h1 className={cx('title')}>{title}</h1>
            <Outlet/>
        </div>
     );
}

export default Content;