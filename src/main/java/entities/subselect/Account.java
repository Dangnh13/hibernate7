package entities.subselect;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "Account")
@Table(name = "account")
@Data
public class Account {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String description;

}
