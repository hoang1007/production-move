package vnu.uet.prodmove.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import vnu.uet.prodmove.domain.Warehouse;


public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
}
