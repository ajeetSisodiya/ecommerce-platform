package productService.dtos;

import productService.constants.ProductCategory;
import productService.constants.ProductStatus;
import productService.model.ProductImage;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(
    Long id,
    String sku,
    String name,
    String description,
    ProductCategory category,
    ProductStatus status,
    BigDecimal price,
    String currency,
    String brand,
    List<ProductAttributeResponse> productAttributes,
    List<ProductImageResponse> imageUrls
) {}

