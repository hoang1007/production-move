package vnu.uet.prodmove.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import vnu.uet.prodmove.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {
}
