package entities.concreteProxy;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name = "SingleChild1")
@Data
public class SingleChild1 extends SingleBase {
    private String child1Prop;

    // Constructor
}