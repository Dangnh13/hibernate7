package entities.embeddedableMapKey;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
public class TouristGuide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(
            name = "guide_locations",
            joinColumns = @JoinColumn(name = "guide_id")
    )
    @MapKeyColumn(name = "location_key")
    private Map<Location, String> locationDetails = new HashMap<>();

    public TouristGuide(String name) {
        this.name = name;
    }

}

