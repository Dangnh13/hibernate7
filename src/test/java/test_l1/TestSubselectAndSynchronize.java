package test_l1;

import entities.subselect.Account;
import entities.subselect.AccountSummary;
import entities.subselect.AccountTransaction;
import entities.subselect.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

public class TestSubselectAndSynchronize {

    @BeforeAll
    public static void setup() {
        System.out.println("Starting tests...");
    }

    @AfterAll
    public static void teardown() {
        HibernateUtil.shutdown();
        System.out.println("Tests finished.");
    }

    @Test
    public void testCustomTypeGender() {
        createFakeData();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            AccountSummary summary = session.createQuery(
                            "from AccountSummary where id = :id", AccountSummary.class)
                    .setParameter("id", 1L)
                    .uniqueResult();

            System.out.println("Client Name: " + summary.getClientName());
            System.out.println("Balance: " + summary.getBalance());
        }
    }

    public static void createFakeData() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Create and persist a client
            Client client = new Client();
            client.setId(1L);
            client.setFirstName("John");
            client.setLastName("Doe");
            session.persist(client);

            // Create and persist an account
            Account account = new Account();
            account.setId(1L);
            account.setClient(client);
            account.setDescription("Checking account");
            session.persist(account);

            // Create and persist account transactions
            AccountTransaction transaction1 = new AccountTransaction();
            transaction1.setAccount(account);
            transaction1.setDescription("Salary");
            transaction1.setCents(100 * 7000);
            session.persist(transaction1);

            AccountTransaction transaction2 = new AccountTransaction();
            transaction2.setAccount(account);
            transaction2.setDescription("Shopping");
            transaction2.setCents(-100 * 2200);
            session.persist(transaction2);

            transaction.commit();
        }
    }
}
