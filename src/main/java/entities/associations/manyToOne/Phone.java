package entities.associations.manyToOne;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Phone")
@Data
public class Phone {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id",foreignKey = @ForeignKey(name = "PERSON_ID_FK"))
    private Person person;

    @Column(name = "`number`")
    private String number;
}
