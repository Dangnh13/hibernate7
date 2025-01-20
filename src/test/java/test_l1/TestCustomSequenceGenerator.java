package test_l1;

import entities.Savings;
import entities.customSequenceGenerator.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.List;

public class TestCustomSequenceGenerator {

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

        Book ebookPublisher = new Book();
        ebookPublisher.setName("Sach tieng viet");
        session.persist(ebookPublisher);
        tx.commit();

        // Read
        session = HibernateUtil.getSessionFactory().openSession();
        List<Book> list = session.createQuery("from Book", Book.class).list();
        System.out.println(list.size());
        session.close();
    }

}
