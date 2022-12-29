import { Link } from 'react-router-dom';
import routes from '~/config/routes';

import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
const cx = ClassNames(style);

function NotFound() {
    return ( 
        <div className={cx('container')}>
            <h1 className={cx('status')}>BigCorp, Opp!</h1>
            <h2 className={cx('status')}>404</h2>
            <p className={cx('message')}>Page not found. That's an error!</p>
            <div >Go to <Link className={cx('navigate-routes')} to={routes.public.login.path}>Log in</Link> now.</div>
        </div>
     );
}

export default NotFound;