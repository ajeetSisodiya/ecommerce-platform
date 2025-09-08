package productService.model;

import jakarta.persistence.*;
import lombok.*;
import productService.constants.ProductCategory;
import productService.constants.ProductStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String sku; // Stock Keeping Unit (unique)

    @Column(length = 1000)
    private String description;

    private BigDecimal price;
    private String currency;
    private String brand;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductAttribute> productAttributes = new ArrayList<>() ;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;
}
