package ph.syphym.evergreen.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ph.syphym.evergreen.constant.RegionEnums;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private UUID id;

    @Size(min = 15, message = "Description must be at least 20 characters long")
    private String name;

    @Size(min = 20, message = "Description must be at least 20 characters long")
    private String description;

    @PositiveOrZero(message = "Price must not be negative")
    private BigDecimal price;

    @NotBlank(message = "Manufacturer is required")
    private String manufacturer;


    @Column(name = "region", nullable = false)
    private RegionEnums region;

    private String category;
}
