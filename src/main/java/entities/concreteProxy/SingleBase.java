package entities.concreteProxy;


import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import org.hibernate.annotations.ConcreteProxy;

@Entity(name = "SingleBase")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "disc_col")
@ConcreteProxy
public class SingleBase {
    @Id
    @GeneratedValue
    private Long id;
}