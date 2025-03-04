package ph.syphym.evergreen.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(schema = "PRODUCT_CRUD", name ="CATEGORIES")
public class Category {

    @Id
    @Column(name = "id")
    private Integer id;

    @Size(min = 20, message = "Name must be at least 20 characters long")
    @Column(name = "name", nullable = false, unique = true)
    private String name;


}
