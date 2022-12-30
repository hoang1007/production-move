import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
const cx = ClassNames(style);

function ProductLine() {
    return ( 
        <div className={cx('container')}>
            ProductLine
        </div>
     );
}

export default ProductLine;