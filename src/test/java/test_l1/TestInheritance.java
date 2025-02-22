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

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testMappedSuperclass() {
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

    @Test
    public void testSingleTable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        var debitAccount = new entities.inheritance.singleTable.DebitAccount();
        debitAccount.setOwner("debit Account 0 ");
        debitAccount.setOverdraftFee(new BigDecimal(1000));
        session.persist(debitAccount);
        var debitAccount1 = new entities.inheritance.singleTable.DebitAccount();
        debitAccount1.setOwner("debit Account 1");
        debitAccount1.setOverdraftFee(new BigDecimal(1111));
        session.persist(debitAccount1);
        var debitAccount2 = new entities.inheritance.singleTable.DebitAccount();
        debitAccount2.setOwner("debit Account 2");
        debitAccount2.setOverdraftFee(new BigDecimal(2000));
        session.persist(debitAccount2);

        var creditAccount = new entities.inheritance.singleTable.CreditAccount();
        creditAccount.setOwner("credit");
        creditAccount.setCreditLimit(new BigDecimal(2000));
        session.persist(creditAccount);
        tx.commit();
        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        var foundDebit = session.find(entities.inheritance.singleTable.DebitAccount.class, debitAccount.getId());
        var foundCre = session.find(entities.inheritance.singleTable.CreditAccount.class, creditAccount.getId());
        System.out.println(foundDebit.toString());
        System.out.println(foundCre.toString());

        System.out.println("-------Tải nhiều entity sử dụng API byMultipleIds của Hibernate ---");
        // binding parameter (1:ARRAY) <- [[1, 2]]
        var debits = session
                .byMultipleIds(entities.inheritance.singleTable.DebitAccount.class)
                .multiLoad(1L, 2L);

        assertEquals(2, debits.size());

        // binding parameter (1:ARRAY) <- [[3]]
        var sameDebits = session
                .byMultipleIds(entities.inheritance.singleTable.DebitAccount.class)
                .enableSessionCheck(true)
                .multiLoad(1L, 2L, 3L);

        assertEquals(debits, sameDebits);
        session.close();
    }

}
