package vnu.uet.prodmove.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vnu.uet.prodmove.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
