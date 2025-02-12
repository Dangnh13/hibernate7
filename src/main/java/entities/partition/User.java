package entities.partition;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.PartitionKey;

@Entity(name = "Users")
@Table(name = "Users")
@Data
@ToString
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String firstname;
    private String lastname;

    @PartitionKey
    private String tenantKey;
}
