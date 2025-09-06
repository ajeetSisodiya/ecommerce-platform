package productService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import productService.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
