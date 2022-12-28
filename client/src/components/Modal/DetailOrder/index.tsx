import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
const cx = ClassNames(style);

function DetailOrderModal() {
    return ( 
        <div id="modal" className={cx('container')}>
            order detail
        </div>
     );
}

export default DetailOrderModal;