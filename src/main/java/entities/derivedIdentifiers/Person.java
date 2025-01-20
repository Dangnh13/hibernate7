package entities.derivedIdentifiers;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

@Entity(name = "Person")
@Data
public class Person  {

    @Id
    private Long id;

    @NaturalId
    private String registrationNumber;

    public Person() {}

    public Person(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
