
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
const cx = ClassNames(style);

function Order() {
    return ( 
        <div className={cx('container')}>
            Order
        </div>
     );
}

export default Order;