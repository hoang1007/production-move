package vnu.uet.prodmove.repos;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vnu.uet.prodmove.entity.ProductDetail;


public interface ProductdetailRepository extends JpaRepository<ProductDetail, Integer> {

    @Query(value="select * from productdetail where agencyID=:agencyId order by ID ASC", nativeQuery = true)
    List<ProductDetail> findByAgencyID(@Param(value="agencyId") Integer agencyId);
}
