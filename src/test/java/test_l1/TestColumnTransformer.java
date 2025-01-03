package test_l1;

import entities.MonetaryAmount;
import entities.Savings;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.Currency;
import java.util.List;

public class TestColumnTransformer {

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
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // Create
        Savings entity = new Savings();
        entity.setWallet(new MonetaryAmount(100, Currency.getInstance("USD")));
        session.persist(entity);

        tx.commit();
        session.close();

        // Read
        session = HibernateUtil.getSessionFactory().openSession();
        List<Savings> list = session.createQuery("from Savings", Savings.class).list();
        System.out.println(list.size());
        session.close();
    }
}
