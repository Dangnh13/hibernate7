package entities.collections.mappingLists;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.ListIndexBase;

@Entity
@Data
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(
            name = "person_addresses",
            joinColumns = @JoinColumn(name = "person_id")
    )
    // Mặc định: cột = <tên thuộc tính>_ORDER, ở đây chỉ định rõ
    @OrderColumn(name = "address_index")
    @ListIndexBase(1)
    private java.util.List<Address> addresses = new java.util.ArrayList<>();

    // Constructors
    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }
}
