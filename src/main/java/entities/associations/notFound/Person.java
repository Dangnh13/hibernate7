package entities.associations.notFound;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity(name = "Person")
@Table(name = "Person")
@Data
public  class Person {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "city_fk", referencedColumnName = "id")
    private City city;
}
