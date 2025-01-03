package entities.compositeUserType;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CompositeType;

import java.util.Set;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue
    private Integer id;

    @Embedded
    @AttributeOverride(name = "firstName", column = @Column(name = "first_name"))
    @AttributeOverride(name = "lastName", column = @Column(name = "last_name"))
    @CompositeType(NameCompositeUserType.class) // Chỉ định CompositeUserType
    private Name name;

    @ElementCollection
    @AttributeOverride(name = "firstName", column = @Column(name = "first_name"))
    @AttributeOverride(name = "lastName", column = @Column(name = "last_name"))
    @CompositeType(NameCompositeUserType.class) // Chỉ định CompositeUserType cho tập hợp
    private Set<Name> aliases;

}

