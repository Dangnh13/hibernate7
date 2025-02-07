package entities.collections.sortedSet;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.SortNatural;

import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    private String nickname;

    @ElementCollection
    @CollectionTable(name = "person_names", joinColumns = @JoinColumn(name = "person_id"))
    @SortNatural // sắp xếp dựa trên Name#compareTo
    private SortedSet<Name> names = new TreeSet<>();

}

