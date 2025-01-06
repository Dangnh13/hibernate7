package entities.physicalNamingStrategy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private int streetCode;

    @Transient
    private String address;

    @Version
    private int version; // Không có getter/setter, nhưng vẫn hoạt động với Hibernate
}
