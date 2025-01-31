package entities.associations.oneToOne.bidirectional;

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

    @OneToOne(mappedBy = "phone",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private PhoneDetails phoneDetails;

    public void addDetails(PhoneDetails details) {
        details.setPhone(this);
        this.phoneDetails = details;
    }

    public void removeDetails() {
        if (phoneDetails != null) {
            phoneDetails.setPhone(null);
            this.phoneDetails = null;
        }
    }
}
