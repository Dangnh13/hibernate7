package entities.associations.anyAnnotation;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

@Entity
@Table(name = "payment_holder")
@Data
@NoArgsConstructor
public class PaymentHolder {

    @Id
    @GeneratedValue
    private Long id;

    // @Any: Mô phỏng "nhiều-1" đa hình
    @Any
    @AnyKeyJavaClass(Long.class)       // Khóa chính cho CardPayment/CheckPayment là kiểu Long
    @JoinColumn(name = "payment_fk")   // Cột chứa ID
    @Column(name = "payment_type")     // Cột chứa discriminator
    @AnyDiscriminatorValue(discriminator = "CARD", entity = CardPayment.class)
    @AnyDiscriminatorValue(discriminator = "CHECK", entity = CheckPayment.class)
    @NotFound(action = NotFoundAction.IGNORE)  // Nếu ID "lạc", coi như null
    private Payment payment;

    private String holderName; // ví dụ cột khác

    public PaymentHolder(String holderName, Payment payment) {
        this.holderName = holderName;
        this.payment = payment;
    }
}
