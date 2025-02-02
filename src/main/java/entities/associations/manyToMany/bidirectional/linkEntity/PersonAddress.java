package entities.associations.manyToMany.bidirectional.linkEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "PersonAddress")
@Data
@AllArgsConstructor
@NoArgsConstructor
public  class PersonAddress {

    @Id
    @ManyToOne
    private Person person;

    @Id
    @ManyToOne
    private Address address;
}
