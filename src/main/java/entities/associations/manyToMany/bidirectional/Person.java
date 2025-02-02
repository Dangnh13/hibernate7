package entities.associations.manyToMany.bidirectional;

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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Address> addresses = new ArrayList<>();

    public void addAddress(Address address) {
        addresses.add(address);
        address.getOwners().add(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.getOwners().remove(this);
    }
}