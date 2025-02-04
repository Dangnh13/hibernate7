package entities.associations.joinFormula;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.JoinFormula;

@Entity(name = "User")
@Table(name = "users")
@Data
public  class User {

    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @ManyToOne
    @JoinFormula("REGEXP_REPLACE(phoneNumber, '\\+(\\d+)-.*', '\\1')::int")
    private Country country;
}