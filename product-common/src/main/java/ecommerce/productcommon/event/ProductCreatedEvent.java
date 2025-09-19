package ecommerce.productcommon.event;

import ecommerce.productcommon.constants.ProductCategory;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record ProductCreatedEvent(
        @NotBlank String sku,
        @NotBlank  String name,
        String description,
        @NotNull ProductCategory category,
        @DecimalMin(value = "0.0", inclusive = false) BigDecimal price,
        @NotBlank String currency,
        @NotBlank String brand,
        List<ProductAttributeEvent> attributes,
        List<ProductImageEvent> images
) {}
