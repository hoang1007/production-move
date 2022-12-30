import {forwardRef} from 'react'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    // Tìm ICON tại https://fontawesome.com/icons
    IconDefinition,
    faUser, 
    faMagnifyingGlass,
    faBell,
    faBorderNone,
    faCubesStacked,
    faCartShopping,
    faScrewdriverWrench,
    faPlus,
    faWarehouse,
    faBoxOpen,
    faGears,
    faEye,
    faEyeSlash,
    faTruck
} from "@fortawesome/free-solid-svg-icons";
import { faFacebook, faGooglePlus } from '@fortawesome/free-brands-svg-icons';
import Tippy from '@tippyjs/react';
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';

const cx = ClassNames(style);

interface TippyWrapperProps {
    tooltip: any,
    children: React.ReactNode
}

export interface CreateIconProps {
    className?: string,
    tooltip?: any,
    onClick?: Function
}

function TippyWrapper({tooltip, children}: TippyWrapperProps) {
    const hidden = !tooltip || Object.keys(tooltip).length === 0;
    const classes = cx({
        hidden: hidden
    })
    return (
        <Tippy {...tooltip} className={classes}>
            {children}
        </Tippy>
    )
}

function CreateIcon(icon: IconDefinition) {
    const Icon = ({ className, tooltip, onClick }: CreateIconProps, ref: React.LegacyRef<HTMLImageElement> | undefined) => {
        return (
            <TippyWrapper tooltip={tooltip}>
                <div
                    className={[cx('container'), className].join(' ')}
                    onClick={() => { onClick && onClick() }}
                    ref={ref}>
                    <FontAwesomeIcon icon={icon}/>
                </div>
            </TippyWrapper>
        );
    }

    return forwardRef(Icon);
}

export const UserIcon = CreateIcon(faUser);
export const FacebookIcon = CreateIcon(faFacebook);
export const GoogleIcon = CreateIcon(faGooglePlus);
export const SearchIcon = CreateIcon(faMagnifyingGlass);
export const NotificationIcon = CreateIcon(faBell);
export const GridIcon = CreateIcon(faBorderNone);
export const StockIcon = CreateIcon(faCubesStacked);
export const GroceryIcon = CreateIcon(faCartShopping)
export const RepairedIcon = CreateIcon(faScrewdriverWrench);
export const PlusIcon = CreateIcon(faPlus);
export const WarehouseIcon = CreateIcon(faWarehouse);
export const BoxIcon = CreateIcon(faBoxOpen)
export const SettingIcon = CreateIcon(faGears)
export const EyeIcon = CreateIcon(faEye);
export const EyeSlashIcon  = CreateIcon(faEyeSlash)
export const DeliveryIcon = CreateIcon(faTruck);
