package entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CompositeType;

@Entity(name = "Savings")
@Data
@ToString
public  class Savings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CompositeType(MonetaryAmountUserType.class)
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "money")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    @ColumnTransformer(
            forColumn = "money",
            read = "money / 100",
            write = "? * 100"
    )
    private MonetaryAmount wallet;

    // Getters và Setters
}


