export const trimObject = (obj: { [key: string]: any }) => {
    if (obj === null || typeof obj !== 'object') return;
    Object.keys(obj).forEach(key => {
        if (typeof obj[key] === 'string') obj[key] = obj[key].trim();
        else if (obj[key] !== null && typeof obj[key] === 'object') {
            trimObject(obj[key])
        }
    })
    return obj
}

export const getRoleInVietNamese = (role:string): string => {
    if (role === 'AGENCY') return 'Đại lý'
    else if (role === 'WARRANTY') return 'Trung tâm bảo hành'
    else if (role === 'FACTORY') return 'Nhà máy sản xuất'
    return ''
}
