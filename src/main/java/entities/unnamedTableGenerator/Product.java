package entities.unnamedTableGenerator;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Product")
@Data
public class Product {

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE
    )
    private Long id;

    @Column(name = "product_name")
    private String name;

}
