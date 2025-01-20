package entities.derivedIdentifiers;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity(name = "PersonDetails")
@Data
public  class PersonDetails  {

    // Id này sẽ được map với person.Id mặc dù không giống tên
    // Mỗi entity phải bắt buộc khai báo 1 @id khoá chính
    @Id
    private Long idPrimary;

    private String nickName;

    @OneToOne
    @MapsId
    private Person person;
}
