
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
const cx = ClassNames(style);

function Dashboard() {
    return ( 
        <div className={cx('container')}>
            Dashboard
        </div>
     );
}

export default Dashboard;