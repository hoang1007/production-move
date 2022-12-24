# Getting Started with Create React App

NOTE:

- Cấu trúc folder: (Xem trong Agency nhé, tôi ví dụ thế )
+ trong mỗi page private thì có file chính là index.tsx với các folder con là Sidebar và Content.
+ Trong Sidebar thì ae viết code trong <SidebarLayout> nhé, mấy cái nút để chuyển routes ấy
+ Trong Content thì sẽ viết nội dung trong <ContentLayout>


Nữa là:

+ Khi import thì viết kiểu '~/' nhé, ~ tượng trưng cho src, như thế thì sẽ tránh bị kiểu ../../../....
+ Ae viết scss nhé, tên file là style.module.scss. ( có đuôi .module.scss )
+ Trong file tsx thì sẽ thêm đoạn code sau:
**
import style from './style.module.scss';
import ClassNames from '~/utils/classNames';
const cx = ClassNames(style);
**
thì khi tạo tên class sẽ là className={cx('tên-class')}. Đây là thư viện classNames với mục đích là các tên class sẽ được thêm các kí tự khác khi render, tránh xung đột css giữa các file khác nếu trùng tên.

Còn 1 số hooks hay component khác sẽ cập nhật sau.