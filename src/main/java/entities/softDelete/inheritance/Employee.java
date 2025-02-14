package entities.softDelete.inheritance;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "employee")
@PrimaryKeyJoinColumn(name = "employee_id")
@Data
@ToString
public class Employee extends BasePerson {

    private Double salary;

    public Employee() {
    }

    public Employee(Long id, String name, Double salary) {
        super(id, name);
        this.salary = salary;
    }
}
