import { TextField, InputAdornment } from '@mui/material';

interface Props {
    className?: string,
    placeholder?:string,
    icon?: React.ReactNode,
    position: 'start' | 'end'
}

function Input({className, placeholder, icon, position}: Props) {
    return (
        <TextField
            className={className}
            label={placeholder}
            InputProps={{
                startAdornment: (
                    <InputAdornment position={position}>
                        {icon}
                    </InputAdornment>
                ),
            }}
            variant="standard"
        />
    );
}

export default Input;