package vnu.uet.prodmove.repos;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vnu.uet.prodmove.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Page<Product> findAll(Pageable pageable);
}
