package vnu.uet.prodmove.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Order;

public interface IOrderService {
    public void saveAll(Collection<Order> orders);
}
