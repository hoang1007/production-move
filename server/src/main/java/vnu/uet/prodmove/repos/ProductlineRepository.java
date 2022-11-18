package vnu.uet.prodmove.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import vnu.uet.prodmove.domain.Productline;


public interface ProductlineRepository extends JpaRepository<Productline, Integer> {
}
