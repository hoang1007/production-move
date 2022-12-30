import * as React from 'react';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import NeedRepair from './NeedRepair';
import ClassNames from '~/utils/classNames';
import style from './style.module.scss';
import Repairing from './Repairing';
import Repaired from './Repaired';
import ReturnedToFactory from './ReturnedToFactory';

const cx = ClassNames(style);

interface TabPanelProps {
    children?: React.ReactNode;
    index: number;
    value: number;
}

function TabPanel(props: TabPanelProps) {
    const { children, value, index, ...other } = props;

    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`simple-tabpanel-${index}`}
            aria-labelledby={`simple-tab-${index}`}
            className={cx('panel')}
            {...other}
        >
            {value === index && (
                children
            )}
        </div>
    );
}

function a11yProps(index: number) {
    return {
        id: `simple-tab-${index}`,
        'aria-controls': `simple-tabpanel-${index}`,
    };
}

export default function Warranty() {
    const [value, setValue] = React.useState(0);

    const handleChange = (event: React.SyntheticEvent, newValue: number) => {
        setValue(newValue);
    };

    return (
        <Box sx={{ width: '100%', height: "100%" }}>
            <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                <Tabs value={value} onChange={handleChange} aria-label="basic tabs example">
                    <Tab label="Cần bảo hành" {...a11yProps(0)} />
                    <Tab label="Đang bảo hành" {...a11yProps(1)} />
                    <Tab label="Đã bảo hành" {...a11yProps(2)} />
                    <Tab label="Không bảo hành được" {...a11yProps(3)} />
                </Tabs>
            </Box>
            <TabPanel value={value} index={0}>
                <NeedRepair />
            </TabPanel>
            <TabPanel value={value} index={1}>
                <Repairing />
            </TabPanel>
            <TabPanel value={value} index={2}>
                <Repaired />
            </TabPanel>
            <TabPanel value={value} index={3}>
                <ReturnedToFactory />
            </TabPanel>
        </Box>
    );
}