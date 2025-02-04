package entities.associations.joinFormula;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "Country")
@Table(name = "countries")
@Data
public  class Country {

    @Id
    private Integer id;
    private String name;
}