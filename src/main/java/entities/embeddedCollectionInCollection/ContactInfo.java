package entities.embeddedCollectionInCollection;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Embeddable
@Data
public class ContactInfo {
    private String phoneNumber;

    @ElementCollection
    private List<String> emails;

}

