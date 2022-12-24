import React from 'react';
import './style.module.scss'

import logo from '~/assets/images/logo.png';
import Image from '~/components/Image';

import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
const cx = ClassNames(style);

interface Props {
    title: 'BIGCORP' | 'AGENCY' | 'WARRANTY' | 'MANUFACTURING BASE',
    children: React.ReactNode
}

const Sidebar = ({title, children}: Props) => {
    return (
        <div className={'sidebar-layout'}>
            <div className={cx('header')}>
                <Image className={cx('logo')} src={logo} />
                <h1 className={cx('title')}>{title}</h1>
            </div>

            <div className={cx('nav')}>
                <div className={cx('title')}>main menu</div>
                {children}
            </div>
        </div>
    )
}

export default Sidebar;