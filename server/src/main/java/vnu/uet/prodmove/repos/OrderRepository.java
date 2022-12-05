package vnu.uet.prodmove.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import vnu.uet.prodmove.entity.Order;


public interface OrderRepository extends JpaRepository<Order, Integer> {
}
