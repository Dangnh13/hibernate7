package entities.embeddableInstantiator;

import lombok.Data;
import org.hibernate.annotations.EmbeddableInstantiator;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue
    private Integer id;

    @Embedded
    @EmbeddableInstantiator(NameInstantiator.class) // Chỉ định instantiator
    private Name name;

    @ElementCollection
    @Embedded
    @EmbeddableInstantiator(NameInstantiator.class)
    private Set<Name> aliases;
}

