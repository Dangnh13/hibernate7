package entities.collections.mappingLists;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address {

    private String city;
    private String street;

    // Constructors
    public Address() {
    }

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }
}
