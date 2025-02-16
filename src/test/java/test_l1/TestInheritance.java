package test_l1;

import entities.associations.anyAnnotation.CardPayment;
import entities.associations.anyAnnotation.CheckPayment;
import entities.associations.anyAnnotation.PaymentHolder;
import entities.inheritance.mappedSuperclass.CreditAccount;
import entities.inheritance.mappedSuperclass.DebitAccount;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.math.BigDecimal;

public class TestInheritance {

    @BeforeAll
    public static void setup() {
        System.out.println("Starting tests...");
    }

    @AfterAll
    public static void teardown() {
        HibernateUtil.shutdown();
        System.out.println("Tests finished.");
    }

    @AfterEach
    public void tearDown() {
        HibernateUtil.shutdown();
        System.out.println("Tests finished.");
    }

    @Test
    public void test() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        DebitAccount debitAccount = new DebitAccount();
        debitAccount.setOwner("debit Account");
        debitAccount.setOverdraftFee(new BigDecimal(1000));
        session.persist(debitAccount);

        CreditAccount creditAccount = new CreditAccount();
        creditAccount.setOwner("credit");
        creditAccount.setCreditLimit(new BigDecimal(2000));
        session.persist(creditAccount);

        tx.commit();

        DebitAccount foundDebit = session.find(DebitAccount.class, debitAccount.getId());
        System.out.println(foundDebit.toString());
        session.close();
    }

}
