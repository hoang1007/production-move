import { CustomerType } from "./TypeGlobal";

export const isOrderSold = (customer: CustomerType) => {
    const orders = customer.orders;
    const latestProduct = orders[0];
    return latestProduct.soldAt !== null;
}