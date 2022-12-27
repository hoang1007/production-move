import { useNavigate } from 'react-router-dom';

import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import { GridIcon, StockIcon, GroceryIcon, RepairedIcon, PlusIcon, WarehouseIcon } from '~/components/Icon';
import { agencyRoutes } from '~/config/routes';

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
			<Button variant="text" className={cx('navigation-btn')} startIcon={<GridIcon className={cx('icon')} />} onClick={(e) => { handleNavigate(e, agencyRoutes.dashboard) }}>
				Dashboard
			</Button>
			<Button variant="text" className={cx('navigation-btn')} startIcon={<PlusIcon className={cx('icon')} />} onClick={(e) => { handleNavigate(e, agencyRoutes.import) }}>
				Import
			</Button>
			<Button variant="text" className={cx('navigation-btn')} startIcon={<RepairedIcon className={cx('icon')} />} onClick={(e) => { handleNavigate(e, agencyRoutes.warranty) }}>
				Warranty
			</Button>
			<Button variant="text" className={cx('navigation-btn')} startIcon={<WarehouseIcon className={cx('icon')} />} onClick={(e) => { handleNavigate(e, agencyRoutes.warehouse) }}>
				Warehouse
			</Button>
			<Button variant="text" className={cx('navigation-btn')} startIcon={<GroceryIcon className={cx('icon')} />} onClick={(e) => { handleNavigate(e, agencyRoutes.order) }}>
				Order
			</Button>
		</Stack>
	);
}

export default Sidebar;