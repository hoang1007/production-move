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
				Thống kê
			</Button>
			<Button variant="text" className={cx('navigation-btn')} startIcon={<PlusIcon className={cx('icon')} />} onClick={(e) => { handleNavigate(e, agencyRoutes.import) }}>
				Nhập sản phẩm mới
			</Button>
			<Button variant="text" className={cx('navigation-btn')} startIcon={<RepairedIcon className={cx('icon')} />} onClick={(e) => { handleNavigate(e, agencyRoutes.warranty) }}>
				Bảo hành
			</Button>
			<Button variant="text" className={cx('navigation-btn')} startIcon={<WarehouseIcon className={cx('icon')} />} onClick={(e) => { handleNavigate(e, agencyRoutes.warehouse) }}>
				Kho
			</Button>
			<Button variant="text" className={cx('navigation-btn')} startIcon={<GroceryIcon className={cx('icon')} />} onClick={(e) => { handleNavigate(e, agencyRoutes.order) }}>
				Đơn đặt hàng
			</Button>
			<Button variant="text" className={cx('navigation-btn')} startIcon={<GroceryIcon className={cx('icon')} />} onClick={(e) => { handleNavigate(e, agencyRoutes.sell) }}>
				Bán hàng
			</Button>
		</Stack>
	);
}

export default Sidebar;