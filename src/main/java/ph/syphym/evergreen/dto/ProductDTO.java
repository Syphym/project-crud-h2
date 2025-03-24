package ph.syphym.evergreen.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ph.syphym.evergreen.constant.RegionEnums;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private UUID id;

    @NotBlank(message = "name is required")
    @Size(min = 10, message = "name must be 10 characters long")
    private String name;

    @NotBlank(message = "description is required")
    @Size(min = 50, message = "description must be 50 characters long")
    private String description;

    @PositiveOrZero(message = "Price must not be negative")
    private Double price;

    @NotBlank(message = "manufacturer is required")
    @Size(min = 10, message = "Manufacturer must be 10 characters long")
    private String manufacturer;

    private RegionEnums region;

    private String category;
}
