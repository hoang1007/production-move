import { useNavigate } from 'react-router-dom';

import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import { GridIcon, UserIcon, BoxIcon, SettingIcon } from '~/components/Icon';
import { adminRoutes } from '~/config/routes';

import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
const cx = ClassNames(style);

function Sidebar() {
    const navigate = useNavigate()

	const handleNavigate = (event: React.MouseEvent<HTMLButtonElement, MouseEvent>, route: string) => {
		// (event.target as Element).classList.add('active-route');
		navigate(route)
	}

	return (
		<Stack id={cx('container')} direction="column" spacing={2}>
			<Button variant="text" className={cx('navigation-btn')} startIcon={<GridIcon className={cx('icon')} />} onClick={(e) => { handleNavigate(e, adminRoutes.dashboard) }}>
				Dashboard
			</Button>
			<Button variant="text" className={cx('navigation-btn')} startIcon={<UserIcon className={cx('icon')} />} onClick={(e) => { handleNavigate(e, adminRoutes.account) }}>
				Account
			</Button>
		<Button variant="text" className={cx('navigation-btn')} startIcon={<BoxIcon className={cx('icon')} />} onClick={(e) => { handleNavigate(e, adminRoutes.productLine) }}>
				Product Line
			</Button>
			<Button variant="text" className={cx('navigation-btn')} startIcon={<SettingIcon className={cx('icon')} />} onClick={(e) => { handleNavigate(e, adminRoutes.setting) }}>
				Setting
			</Button>
		</Stack>
	);
}

export default Sidebar;