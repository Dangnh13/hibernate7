package entities.idClassWithPartialGeneration;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class PK implements Serializable {

    private String subsystem;
    private String username;
    private Integer registrationId;

    public PK(String subsystem, String username) {
        this.subsystem = subsystem;
        this.username = username;
    }

    public PK(String subsystem, String username, Integer registrationId) {
        this.subsystem = subsystem;
        this.username = username;
        this.registrationId = registrationId;
    }

    private PK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PK pk = (PK) o;
        return Objects.equals(subsystem, pk.subsystem) &&
                Objects.equals(username, pk.username) &&
                Objects.equals(registrationId, pk.registrationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subsystem, username, registrationId);
    }
}
