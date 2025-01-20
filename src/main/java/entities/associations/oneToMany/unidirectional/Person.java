package entities.associations.oneToMany.unidirectional;

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

    // Do có cascade = ALL, các đối tượng Phone trong phones cũng được chèn (ID=1,2).
    // Xóa bản ghi Phone ID=1 (nhờ orphanRemoval = true).
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones = new ArrayList<>();
}
