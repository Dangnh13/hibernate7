package entities.embeddedId.withManytoOne;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "Subsystem")
@Data
public class Subsystem {

    @Id
    @GeneratedValue
    private String id;

    private String description;
}
