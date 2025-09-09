package productService.dtos;

import productService.constants.ProductCategory;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequest(
        String sku,
        String name,
        String description,
        ProductCategory category,
        BigDecimal price,
        String currency,
        String brand,
        List<ProductAttributeRequest> productAttributes,
        List<ProductImageReqeust> imageUrls
) { }
