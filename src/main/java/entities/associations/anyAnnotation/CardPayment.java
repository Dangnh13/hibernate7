package entities.associations.anyAnnotation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "card_payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardPayment implements Payment{
    @Id
    private Long id;
    private String cardNumber;
    private String cardHolderName;

    @Override
    public String getPaymentInfo() {
        return "CardPayment: " + cardNumber + " / " + cardHolderName;
    }
}
