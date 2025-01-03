package entities.embeddableAndImplicitNamingStrategyComponentPathImpl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
@AllArgsConstructor
public class Publisher {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

}

