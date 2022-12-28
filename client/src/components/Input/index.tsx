import { TextField, InputAdornment } from '@mui/material';

interface Props {
    className?: string,
    placeholder?:string,
    icon?: React.ReactNode,
    position?: 'start' | 'end',
    type?: string,
    [key:string]: any
}

function Input({className, placeholder, icon, position, type, ...rest}: Props) {
    return (
        <TextField
            className={className}
            label={placeholder}
            InputProps={{
                startAdornment: position && (
                    <InputAdornment position={position}>
                        {icon}
                    </InputAdornment>
                ),
            }}
            type={type}
            variant="standard"
            {...rest}
        />
    );
}

export default Input;