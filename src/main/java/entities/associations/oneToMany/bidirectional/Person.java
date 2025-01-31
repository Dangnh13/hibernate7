package entities.associations.oneToMany.bidirectional;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Person")
@Data
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "person111", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones = new ArrayList<>();

    public void addPhone(Phone phone) {
        phones.add(phone);
        phone.setPerson111(this);
    }

    public void removePhone(Phone phone) {
        phones.remove(phone);
        phone.setPerson111(null);
    }
}
