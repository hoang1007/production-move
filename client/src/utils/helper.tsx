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