package productService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import productService.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Product getProductById(Long id);
}
