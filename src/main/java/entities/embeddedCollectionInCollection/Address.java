package entities.embeddedCollectionInCollection;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Embeddable
@Data
public class Address {
    @ElementCollection
    private List<String> streets; // Lồng collection trong embeddable
}