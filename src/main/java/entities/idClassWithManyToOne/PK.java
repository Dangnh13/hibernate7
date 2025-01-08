package entities.idClassWithManyToOne;

import lombok.Data;

import java.io.Serializable;

@Data
public class PK implements Serializable {
    private Subsystem subsystem;

    private String username;

    public PK(Subsystem subsystem, String username) {
        this.subsystem = subsystem;
        this.username = username;
    }
    private PK() {
    }
}
