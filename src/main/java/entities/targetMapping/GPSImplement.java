package entities.targetMapping;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GPSImplement implements Coordinates {
    private double latitude;  // Tọa độ vĩ độ
    private double longitude; // Tọa độ kinh độ
    @Override
    public double x() {
        return latitude;
    }
    @Override
    public double y() {
        return longitude;
    }
}

