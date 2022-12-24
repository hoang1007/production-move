import {SearchIcon, NotificationIcon} from '~/components/Icon'
import Input from '~/components/Input';
import Image from '~/components/Image';

import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
const cx = ClassNames(style);

interface Props {
    className?: string
}

function Header({ className }: Props) {
    
    const classes = [cx('container'), 'header-layout', className].join(' ')

    return (
        <div className={classes}>
            <Input
                className={cx('search-input')}
                icon={<SearchIcon className={cx('icon-search')} />}
                position='start'
            />
            
            <div className={cx('actions')}>
                <div className={cx('noti')}>
                    <NotificationIcon className={cx('icon')} />
                    <span className={cx('no-noti')}>2</span>
                </div>

                <div className={cx('user')}>
                    <Image className={cx('avatar')} />
                    <span className={cx('username')}>Name</span>
                </div>
            </div>
        </div>
    );
}

export default Header;