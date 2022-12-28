import React from 'react';

function useModal() {
    const [isOpen, setOpen] = React.useState(false);

    const close = () => {
        setOpen(false);
    }
    const open = () => {
        setOpen(true);
    }

    return [open, close];
}

export default useModal;