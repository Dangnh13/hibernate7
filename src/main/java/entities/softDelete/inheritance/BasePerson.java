package entities.softDelete.inheritance;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.type.YesNoConverter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SoftDelete(
        strategy = SoftDeleteType.DELETED,    // Chiến lược: `true` => "đã xóa"
        columnName = "removed",               // Cột DB tên "removed"
        converter = YesNoConverter.class      // Lưu 'Y' cho true, 'N' cho false
)
@Data
@ToString
public abstract class BasePerson {

    @Id
    private Long id;

    private String name;

    // Constructors
    public BasePerson() {
    }

    public BasePerson(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
