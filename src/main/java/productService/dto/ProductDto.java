package productService.dto;

import lombok.Data;
import productService.constants.ProductCategory;
import productService.constants.ProductStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDto {
    private String name;
    private String sku; // Stock Keeping Unit (unique)
    private String description;
    private BigDecimal price;
    private String currency;
    private String brand;
    private ProductStatus status;
    private List<ProductAttributeDto> productAttributes = new ArrayList<>();
    private ProductCategory category;
}

