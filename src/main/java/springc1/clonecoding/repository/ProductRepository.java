package springc1.clonecoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springc1.clonecoding.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
