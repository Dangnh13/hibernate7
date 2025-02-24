package test_l1;

import entities.associations.anyAnnotation.CardPayment;
import entities.associations.anyAnnotation.CheckPayment;
import entities.associations.anyAnnotation.PaymentHolder;
import entities.filter.sQLJoinTableRestriction.Book;
import entities.filter.sQLJoinTableRestriction.Reader;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

public class TestFilterEntities {

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
    public void testSQLJoinTableRestriction() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Reader reader1 = new Reader();
        reader1.setId(1L);
        reader1.setName("John Doe");
        session.persist(reader1);

        Reader reader2 = new Reader();
        reader2.setId(2L);
        reader2.setName("John Doe Jr.");
        session.persist(reader2);

        Book book = new Book();
        book.setId(1L);
        book.setTitle("High-Performance Java Persistence");
        book.setAuthor("Vad Mihalcea");
        book.getCurrentWeekReaders().add(reader1);
        book.getCurrentWeekReaders().add(reader2);
        session.persist(book);

        tx.commit();
        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        // 4) Đọc lại PaymentHolder từ DB
        var reloaded1 = session.find(Book.class, book.getId());

        System.out.println("reloaded1 => " + reloaded1);
        session.close();
    }

}
