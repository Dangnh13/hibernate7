package entities.compositeIdentifier;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "Book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Publisher publisher;

    @Id
    private String title;
}



