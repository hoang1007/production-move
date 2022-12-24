import { useState, forwardRef, useEffect } from 'react';

import DEFAULT_IMAGE from '~/assets/images/default_img.jpg';
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
const cx = ClassNames(style);

interface Props {
    className?: string
    src?: string,
    alt?: string,
    fallback?: string,
}

function Image({
    src,
    alt,
    fallback,
    className,
    ...rest
}: Props, ref: React.LegacyRef<HTMLImageElement> | undefined) {
    const [source, setSource] = useState(src || DEFAULT_IMAGE);

    useEffect(() => {
        setSource(src || DEFAULT_IMAGE);
    }, [src])
    
    const handleOnError = () => {
        setSource(fallback || DEFAULT_IMAGE);
    }
    
    return (
        <div
            className={[className, cx('container')].join(' ')}
        >
            <img
                src={source}
                alt={alt || 'no-image'}
                onError={handleOnError}
                ref={ref}
                {...rest}
            />
        </div>
    );
}

export default forwardRef(Image);