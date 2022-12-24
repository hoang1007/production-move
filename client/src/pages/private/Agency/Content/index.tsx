import ContentLayout from '~/pages/components/ContentLayout';

import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
const cx = ClassNames(style);

function Content() {
    return ( 
        <ContentLayout title={"Dashboard"}>
            {/* Write your code here */}
        </ContentLayout>
     );
}

export default Content;