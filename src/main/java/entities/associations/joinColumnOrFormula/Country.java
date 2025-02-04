package entities.associations.joinColumnOrFormula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity(name = "Country")
@Table(name = "countries")
@Data
public class Country implements Serializable {
    @Id
    private Integer id;

    private String name;

    private String primaryLanguage;

    @Column(name = "is_default")
    private boolean _default;
}
