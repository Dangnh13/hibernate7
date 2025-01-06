package entities.concreteProxy;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name = "SingleSubChild1")
@Data
public class SingleSubChild1 extends SingleChild1 {
    private String subChild1Prop;

    // Constructor
}
