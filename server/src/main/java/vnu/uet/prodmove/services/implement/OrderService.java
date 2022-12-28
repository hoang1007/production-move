package vnu.uet.prodmove.services.implement;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Order;
import vnu.uet.prodmove.repos.OrderRepository;
import vnu.uet.prodmove.services.IOrderService;

@Service
public class OrderService implements IOrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void saveAll(Collection<Order> orders) {
        System.out.println("save orders");
        orderRepository.saveAll(orders);
    }
    
}
