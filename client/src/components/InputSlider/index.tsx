import * as React from 'react';
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Slider from '@mui/material/Slider';
import MuiInput from '@mui/material/Input';
import VolumeUp from '@mui/icons-material/VolumeUp';

const Input = styled(MuiInput)`
  width: 4rem;
`;

export interface InputSliderProps {
    value: number;
    setValue: (value: number) => void;
    min: number;
    max: number;
    title?: string;
    icon?: React.ReactNode;
}

const InputSlider: React.FC<InputSliderProps> = ({ value, setValue, min, max, title, icon }) => {
    const [isEmpty, setIsEmpty] = React.useState(false);
    
    const handleSliderChange = (event: Event, newValue: number | number[]) => {
        if (typeof newValue === 'number') {
            setValue(newValue);
        } else {
            throw new Error('newValue is not a number');
        }
    };

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        if (event.target.value === '') {
            setIsEmpty(true);
            setValue(0);
        } else {
            setIsEmpty(false);
            setValue(Number(event.target.value));
        }
    };

    const handleBlur = () => {
        if (value < min) {
            setValue(min);
        } else if (value > max) {
            setValue(max);
        }
    };

    return (
        <Box sx={{ width: 200 }}>
            <Typography id="input-slider" gutterBottom>
                {title}
            </Typography>
            <Grid container spacing={2} alignItems="center">
                <Grid item>{icon}</Grid>
                <Grid item xs>
                    <Slider
                        value={typeof value === 'number' ? value : 0}
                        onChange={handleSliderChange}
                        aria-labelledby="input-slider"
                        valueLabelDisplay='auto'
                        min={min}
                        max={max}
                    />
                </Grid>
                <Grid item>
                    <Input
                        value={isEmpty ? '' : value}
                        size="small"
                        onChange={handleInputChange}
                        onBlur={handleBlur}
                        inputProps={{
                            step: 1,
                            min: min,
                            max: max,
                            type: 'number',
                            'aria-labelledby': 'input-slider',
                        }}
                    />
                </Grid>
            </Grid>
        </Box>
    );
}

export default InputSlider;