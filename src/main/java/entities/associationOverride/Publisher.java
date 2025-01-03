package entities.associationOverride;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@Embeddable
@Data
@AllArgsConstructor @NoArgsConstructor
public class Publisher {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

}

