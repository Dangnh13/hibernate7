package entities.idClassExample;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class OrderId implements Serializable {

    private Long orderId;
    private Long productId;

    public OrderId() {}

    public OrderId(Long orderId, Long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    // Getters, setters, equals, and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderId orderId1 = (OrderId) o;
        return Objects.equals(orderId, orderId1.orderId) &&
                Objects.equals(productId, orderId1.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }
}
