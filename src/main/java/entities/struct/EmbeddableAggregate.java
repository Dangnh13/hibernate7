package entities.struct;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Struct;

@Data
@Embeddable
public class EmbeddableAggregate {

    private int age;
    private String address;
}
