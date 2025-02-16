package entities.inheritance.singleTable;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@Entity(name = "CreditAccount")
public class CreditAccount extends Account {

    private BigDecimal creditLimit;

}
