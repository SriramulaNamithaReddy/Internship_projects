package in.nami.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nami.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	 List<Product> findByNameContainingIgnoreCase(String name);
}
