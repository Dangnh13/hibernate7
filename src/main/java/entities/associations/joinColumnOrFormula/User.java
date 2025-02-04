package entities.associations.joinColumnOrFormula;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinFormula;

@Entity(name = "User")
@Table(name = "users")
@Data
public class User {

    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private String language;

    @ManyToOne
    @JoinColumnOrFormula(
            column = @JoinColumn(
                    name = "language",
                    referencedColumnName = "primaryLanguage",
                    insertable = false,
                    updatable = false
            )
    )
    @JoinColumnOrFormula(
            formula = @JoinFormula(
                    value = "true",
                    referencedColumnName = "is_default"
            )
    )
    private Country country;
}
