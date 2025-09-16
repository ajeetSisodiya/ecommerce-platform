package productService.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import productService.constants.ProductCategory;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequest(
        @NotBlank String sku,
        @NotBlank  String name,
        String description,
        @NotNull ProductCategory category,
        @DecimalMin(value = "0.0", inclusive = false) BigDecimal price,
        @NotBlank String currency,
        @NotBlank String brand,
        List<ProductAttributeRequest> productAttributes,
        List<ProductImageReqeust> imageUrls
) { }
