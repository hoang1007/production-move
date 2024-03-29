package vnu.uet.prodmove.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vnu.uet.prodmove.entity.Agency;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Integer> {
}
