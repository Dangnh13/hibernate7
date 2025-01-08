package entities.idClassWithManyToOne;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "Subsystem")
@Data
public class Subsystem {

    @Id
    @GeneratedValue
    private String id;

    private String description;
}