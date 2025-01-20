package entities.customSequenceGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id
    @Sequence(name="my_sequence", startWith=10, incrementBy=5)
    private Long id;
    private String name;
}
