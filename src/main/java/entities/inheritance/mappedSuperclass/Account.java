package entities.inheritance.mappedSuperclass;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@MappedSuperclass
@Data
@ToString
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String owner;

    private BigDecimal balance;

    private BigDecimal interestRate;

}
