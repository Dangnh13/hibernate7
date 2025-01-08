package entities.embeddedId.withManytoOne;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
public class PK implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    private Subsystem subsystem;

    private String username;

    public PK(Subsystem subsystem, String username) {
        this.subsystem = subsystem;
        this.username = username;
    }

    private PK() {
    }
}
