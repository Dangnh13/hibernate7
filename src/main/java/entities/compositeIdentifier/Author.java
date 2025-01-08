package entities.compositeIdentifier;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Entity(name = "Author")
@Data
@EqualsAndHashCode
public  class Author implements Serializable {
    @Id
    private String name;
}

