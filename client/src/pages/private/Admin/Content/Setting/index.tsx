import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
const cx = ClassNames(style);

function Setting() {
    return ( 
        <div className={cx('container')}>
            Setting
        </div>
     );
}

export default Setting;