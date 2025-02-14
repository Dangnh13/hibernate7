package entities.softDelete;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.PartitionKey;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

@Entity(name = "Users")
@Table(name = "Users")
@Data
@ToString
@SoftDelete(strategy = SoftDeleteType.ACTIVE, columnName = "active_khong")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String firstname;

}
