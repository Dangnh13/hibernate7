package entities.struct;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Struct;
import org.hibernate.type.SqlTypes;

@Entity(name = "StructHolder")
@Data
public class StructHolder {

    @Id
    @GeneratedValue
    private Long id;

    @Struct(name = "structType")
    private EmbeddableAggregate aggregate;

    @JdbcTypeCode(SqlTypes.JSON)
    private EmbeddableAggregate aggregateJson;

    private String uname;
}

