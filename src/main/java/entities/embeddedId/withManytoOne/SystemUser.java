package entities.embeddedId.withManytoOne;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name = "SystemUser")
@Data
public class SystemUser {

    @EmbeddedId
    private PK pk;

    private String name;

}
