package entities.subselect;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@Entity(name = "AccountSummary")
@Subselect(
        "select " +
                "	a.id as id, " +
                "	concat(concat(c.first_name, ' '), c.last_name) as clientName, " +
                "	sum(atr.cents) as balance " +
                "from account a " +
                "join client c on c.id = a.client_id " +
                "join account_transaction atr on a.id = atr.account_id " +
                "group by a.id, concat(concat(c.first_name, ' '), c.last_name)"
)
@Synchronize({"account_transaction", "client", "account" })
@Data
public class AccountSummary {
    @Id
    private Long id;
    private String clientName;
    private int balance;
}

