package entities.associations.anyAnnotation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "check_payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckPayment implements Payment {

    @Id
    private Long id;

    private String checkNumber;
    private String bankName;

    @Override
    public String getPaymentInfo() {
        return "CheckPayment: " + checkNumber + " / " + bankName;
    }
}
