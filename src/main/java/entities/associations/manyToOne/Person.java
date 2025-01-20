package entities.associations.manyToOne;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "Person")
@Data
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String uname;
}
