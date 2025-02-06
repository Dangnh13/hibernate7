package entities.collections.sortedSet;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Name implements Comparable<Name> {

    private String first;
    private String last;

    public Name() {}

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public int compareTo(Name o) {
        // So sánh theo last name, rồi first name
        int cmp = this.last.compareTo(o.last);
        if (cmp == 0) {
            cmp = this.first.compareTo(o.first);
        }
        return cmp;
    }
}

