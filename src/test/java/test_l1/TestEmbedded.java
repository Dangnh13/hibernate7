package test_l1;

import entities.attributeOverride.Book;
import entities.attributeOverride.Location;
import entities.attributeOverride.Publisher;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.List;

public class TestEmbedded {

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
        Book entity = new Book();
        entity.setTitle("SACH NGUOI LON");
        entity.setPublisher(new Publisher("Ngo hai dang", new Location("Viet nam", "Ha Noi")));
        session.persist(entity);

        tx.commit();
        session.close();

        // Read
        session = HibernateUtil.getSessionFactory().openSession();
        List<Book> list = session.createQuery("from Book", Book.class).list();
        System.out.println(list.size());
        session.close();
    }
}
