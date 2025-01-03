package entities.embeddableInstantiator;

import jakarta.persistence.*;
import org.hibernate.annotations.EmbeddableInstantiator;

@Embeddable
public class Name {

    @Column(name = "first_name")
    private final String first;

    @Column(name = "last_name")
    private final String last;

    // Constructor mặc định không hợp lệ
    private Name() {
        throw new UnsupportedOperationException();
    }

    // Constructor với tham số
    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String getFirstName() {
        return first;
    }

    public String getLastName() {
        return last;
    }
}

