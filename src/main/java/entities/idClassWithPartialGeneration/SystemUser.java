package entities.idClassWithPartialGeneration;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Entity(name = "SystemUser")
@IdClass(PK.class)
@Data
public  class SystemUser {

    @Id
    private String subsystem;

    @Id
    private String username;

    @Id
    @GeneratedValue
    private Integer registrationId;

    private String name;

    public PK getId() {
        return new PK(
                subsystem,
                username,
                registrationId
        );
    }

}

