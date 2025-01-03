package entities.embeddableAndImplicitNamingStrategyComponentPathImpl;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

@Entity
@Data
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    private String name;

    public Country(String name) {
        this.name = name;
    }
}
