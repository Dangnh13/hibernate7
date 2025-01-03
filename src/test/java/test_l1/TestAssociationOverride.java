package test_l1;

import entities.associationOverride.Book;
import entities.associationOverride.Country;
import entities.associationOverride.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import util.HibernateUtil;

public class TestAssociationOverride {

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
        // Tạo đối tượng Country
        Country usa = new Country("USA");
        Country uk = new Country("UK");
        session.persist(usa);
        session.persist(uk);

        // Tạo đối tượng Publisher
        Publisher ebookPublisher = new Publisher("Ebook Publisher", usa);
        Publisher paperBackPublisher = new Publisher("Paperback Publisher", uk);

        // Tạo đối tượng Book
        Book book = new Book("Hibernate Mastery", "John Doe", ebookPublisher, paperBackPublisher);
        session.persist(book);

        tx.commit();
        session.close();
    }

}
