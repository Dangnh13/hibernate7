package entities.associations.oneToOne.unidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Phone")
@Data
public class Phone {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "`number`")
    private String number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_detail_id")
    private PhoneDetails phoneDetails;

}
