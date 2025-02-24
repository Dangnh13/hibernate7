package entities.filter.sQLJoinTableRestriction;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Entity(name = "Reader")
@Data
@ToString
public  class Reader {
    @Id
    private Long id;

    private String name;
}
