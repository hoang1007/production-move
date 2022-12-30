import { useNavigate } from 'react-router-dom';

import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import { GridIcon, PlusIcon } from '~/components/Icon';

import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
import { warrantyRoutes } from '~/config/routes';
const cx = ClassNames(style);

function Sidebar() {
	const navigate = useNavigate()

	return (
		<Stack id={cx('container')} direction="column" spacing={2}>
			<Button variant="text" className={cx('navigation-btn')} startIcon={<GridIcon className={cx('icon')} />} onClick={() => navigate(warrantyRoutes.dashboard)}>
				Thống kê
			</Button>
			<Button variant="text" className={cx('navigation-btn')} startIcon={<PlusIcon className={cx('icon')} />} onClick={() => navigate(warrantyRoutes.receiveProduct)}>
				Nhận sản phẩm
			</Button>
			<Button variant="text" className={cx('navigation-btn')} startIcon={<PlusIcon className={cx('icon')} />} onClick={() => navigate(warrantyRoutes.repairing)}>
				Sửa sản phẩm
			</Button>
		</Stack>
	);
}

export default Sidebar;