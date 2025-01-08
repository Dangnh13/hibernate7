package entities.idClassWithManyToOne;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "SystemUser")
@IdClass(PK.class)
@Data
public class SystemUser {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Subsystem subsystem;

    @Id
    private String username;

    private String name;
}

