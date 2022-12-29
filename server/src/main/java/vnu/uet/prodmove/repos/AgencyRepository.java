package vnu.uet.prodmove.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.utils.RawQueries;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Integer> {

}

