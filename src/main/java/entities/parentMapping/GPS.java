package entities.parentMapping;

import jakarta.persistence.Embeddable;
import lombok.Data;
import org.hibernate.annotations.Parent;

@Embeddable
@Data
public class GPS {

    private double latitude;
    private double longitude;

    @Parent // Tham chiếu ngược đến Entity sở hữu
    private City city;

    public GPS() {}
    public GPS(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
