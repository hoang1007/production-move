
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
const cx = ClassNames(style);

function Stock() {
    return ( 
        <div className={cx('container')}>
            Stock
        </div>
     );
}

export default Stock;