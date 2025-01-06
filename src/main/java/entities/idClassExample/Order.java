package entities.idClassExample;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "`Order`")
@IdClass(OrderId.class)
@Data
public class Order {

    @Id
    private Long orderId;

    @Id
    private Long productId;

    private int quantity;

    public Order() {}

    public Order(Long orderId, Long productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters and setters
}

