package test_l1;

import entities.associationOverride.Book;
import entities.associationOverride.Country;
import entities.associationOverride.Publisher;
import entities.idClassExample.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

public class TestIdClass {

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
    public void testAssociationOverride() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Order o = new Order();
        o.setQuantity(1);
        o.setOrderId(1l);
        o.setProductId(2l);
        session.persist(o);

        tx.commit();
        session.close();
    }

}
