
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
const cx = ClassNames(style);

function Warranty() {
    return ( 
        <div className={cx('container')}>
            Warranty
        </div>
     );
}

export default Warranty;