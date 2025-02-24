package entities.filter.sQLJoinTableRestriction;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLJoinTableRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Book")
@Data
public class Book {

    @Id
    private Long id;

    private String title;

    private String author;

    @ManyToMany
    @JoinTable(
            name = "Book_Reader",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "reader_id")
    )
    @SQLJoinTableRestriction("created_on > DATEADD('DAY', -7, CURRENT_TIMESTAMP())")
    private List<Reader> currentWeekReaders = new ArrayList<>();
}



