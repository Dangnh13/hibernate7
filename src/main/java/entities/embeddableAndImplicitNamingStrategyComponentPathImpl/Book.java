package entities.embeddableAndImplicitNamingStrategyComponentPathImpl;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String author;

    @Embedded
    private Publisher ebookPublisher;

    @Embedded
    private Publisher paperBackPublisher;

    public Book(String title, String author, Publisher ebookPublisher, Publisher paperBackPublisher) {
        this.title = title;
        this.author = author;
        this.ebookPublisher = ebookPublisher;
        this.paperBackPublisher = paperBackPublisher;
    }
}

