package entities.softDelete.inheritance;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "customer")
@PrimaryKeyJoinColumn(name = "customer_id")
@Data
public class Customer extends BasePerson {

    private String membershipLevel;

    public Customer() {
    }

    public Customer(Long id, String name, String membershipLevel) {
        super(id, name);
        this.membershipLevel = membershipLevel;
    }

}

