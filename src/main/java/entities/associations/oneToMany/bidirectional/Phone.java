package entities.associations.oneToMany.bidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Phone")
@Data
public class Phone {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Person person111;

    @Column(name = "`number`")
    private String number;
}
