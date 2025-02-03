package entities.associations.notFound;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity(name = "City")
@Table(name = "City")
@Data
public  class City implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
}
