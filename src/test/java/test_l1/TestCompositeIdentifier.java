package test_l1;

import entities.Savings;
import entities.compositeIdentifier.Author;
import entities.compositeIdentifier.Book;
import entities.compositeIdentifier.Publisher;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.List;

public class TestCompositeIdentifier {

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
        Author author = new Author();
        author.setName("Author name");
        // Tạo đối tượng Publisher
        Publisher ebookPublisher = new Publisher();
        ebookPublisher.setName("JAVA 17");
        session.persist(author);
        session.persist(ebookPublisher);
        // Tạo đối tượng Book
        Book book = new Book(author, ebookPublisher, "Title sach");
        session.persist(book);

        tx.commit();
        // Read
        session = HibernateUtil.getSessionFactory().openSession();
        List<Book> list = session.createQuery("from Book", Book.class).list();
        System.out.println(list.size());

        Book condition = new Book();
        author.setName("Wrong author name");
        condition.setAuthor(author);
        condition.setPublisher(ebookPublisher);
        condition.setTitle("Title sach");
        Book bookFound = session.find( Book.class, condition);
        session.close();
    }

}
