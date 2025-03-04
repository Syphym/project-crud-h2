package ph.syphym.evergreen.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ph.syphym.evergreen.constant.RegionEnums;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(schema = "PRODUCT_CRUD", name ="PRODUCTS")
public class Product {

    @Id
    @Column(name = "id")
    private UUID id;

    @Size(min = 15, message = "Description must be at least 15 characters long")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min = 20, message = "Description must be at least 20 characters long")
    @Column(name = "description", nullable = false)
    private String description;

    @PositiveOrZero(message = "Price must not be negative")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @NotBlank(message = "Manufacturer is required")
    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "region", nullable = false)
    @Enumerated(EnumType.STRING)
    private RegionEnums region;

    //KEEP IN MIND YOU NEED TO PUT THIS ANNOTATION WHERE YOU THINK IS THE PRIMARY TABLE
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

}
