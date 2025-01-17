package entities.namedSequence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Product")
@Data
public class Product {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "explicit_product_sequence"
    )
    private Long id;

    @Column(name = "product_name")
    private String name;
}

