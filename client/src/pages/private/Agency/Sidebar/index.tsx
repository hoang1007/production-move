import { Link, useNavigate } from 'react-router-dom';

import SidebarLayout from '~/pages/components/SidebarLayout';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import { GridIcon, StockIcon, GroceryIcon, RepairedIcon, PlusIcon } from '~/components/Icon';
import { agencyRoutes } from '~/config/routes';

import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
const cx = ClassNames(style);

function Sidebar() {
    const navigate = useNavigate()

        return (
                <Stack id={cx('container')} direction="column" spacing={2}>
                        <Button variant="text" className={cx('navigation-btn')} startIcon={<GridIcon className={cx('icon')} />} onClick={() => navigate(agencyRoutes.dashboard)}>
                                Dashboard
                        </Button>
                        <Button variant="text" className={cx('navigation-btn')} startIcon={<PlusIcon className={cx('icon')} />} onClick={() => navigate(agencyRoutes.import)}>
                                Import
                        </Button>
                        <Button variant="text" className={cx('navigation-btn')} startIcon={<RepairedIcon className={cx('icon')} />} onClick={() => navigate(agencyRoutes.warranty)}>
                                Warranty
                        </Button>
                        <Button variant="text" className={cx('navigation-btn')} startIcon={<GroceryIcon className={cx('icon')} />} onClick={() => navigate(agencyRoutes.order)}>
                                Order
                        </Button>
                        <Button variant="text" className={cx('navigation-btn')} startIcon={<StockIcon className={cx('icon')} />} onClick={() => navigate(agencyRoutes.stock)}>
                                Stock
                        </Button>
                </Stack>
        );
}

export default Sidebar;