package entities.associations.manyToMany.bidirectional.linkEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Address")
@Data
public  class Address {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(
            mappedBy = "address",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PersonAddress> owners = new ArrayList<>();
}
