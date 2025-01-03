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

  /*
    // Không thể chứa 1 embeddable collection mà embeddable đó lại chứa tiếp 1 collection nữa
    // Sẽ gây lỗi ngay
    @ElementCollection
    private List<Address> emails;*/

}

