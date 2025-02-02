package entities.associations.manyToMany.bidirectional.linkEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Person")
@Data
public  class Person {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(
            mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PersonAddress> personAddresses = new ArrayList<>();

    public void addAddress(Address address) {
        PersonAddress personAddress = new PersonAddress(this, address);
        personAddresses.add(personAddress);
        address.getOwners().add(personAddress);
    }

    public void removeAddress(Address address) {
        PersonAddress personAddress = new PersonAddress(this, address);
        address.getOwners().remove(personAddress);
        personAddresses.remove(personAddress);
        personAddress.setPerson(null);
        personAddress.setAddress(null);
    }
}
