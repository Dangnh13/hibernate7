package entities.attributeOverride;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {

    private String name;

    @Embedded
    @AttributeOverride(name = "country", column = @Column(name = "publisher_country"))
    private Location location;
}
