package entities.inheritance.mappedSuperclass;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Entity(name = "DebitAccount")
@Data
@ToString
public class DebitAccount extends Account {

    private BigDecimal overdraftFee;
}
