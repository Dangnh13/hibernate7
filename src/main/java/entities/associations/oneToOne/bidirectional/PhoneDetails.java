package entities.associations.oneToOne.bidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PhoneDetails {

    @Id
    @GeneratedValue
    private Long id;

    private String provider;

    private String technology;

    @OneToOne(fetch=  FetchType.LAZY)
    @JoinColumn(name = "phone_id")
    private Phone phone;
}
