package entities.unnamedTableGenerator;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "People")
@Data
public class People {

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "table-generator"
    )
    @TableGenerator(
            name =  "table-generator",
            table = "table_identifier",
            pkColumnName = "table_name",
            valueColumnName = "product_id",
            allocationSize = 5
    )
    private Long id;

    @Column(name = "product_name")
    private String name;

}
