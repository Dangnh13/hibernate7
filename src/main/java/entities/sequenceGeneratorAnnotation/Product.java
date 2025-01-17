package entities.sequenceGeneratorAnnotation;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Product")
@Data
public  class Product {

    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "sequence-generator"
    )
    @SequenceGenerator(
            name = "sequence-generator",
            sequenceName = "explicit_product_sequence",
            allocationSize = 5
    )
    private Long id;

    @Column(name = "product_name")
    private String name;
}

