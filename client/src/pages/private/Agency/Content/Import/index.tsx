
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
const cx = ClassNames(style);

function Import() {
    return ( 
        <div className={cx('container')}>
            Import
        </div>
     );
}

export default Import;