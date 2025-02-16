package entities.inheritance.singleTable;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Entity(name = "Account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@ToString
public  class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String owner;
    private BigDecimal balance;
    private BigDecimal interestRate;
}


