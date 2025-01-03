package test_l1;

import entities.embeddableAndImplicitNamingStrategyComponentPathImpl.Book;
import entities.embeddableAndImplicitNamingStrategyComponentPathImpl.Country;
import entities.embeddableAndImplicitNamingStrategyComponentPathImpl.Publisher;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;
/*
Cần cấu hình thêm như dưới đây trong file hibernate_pg.cfg.xml
<property name="hibernate.implicit_naming_strategy">
        org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl
</property>*/
public class TestEmbeddableAndImplicitNamingStrategyComponentPathImpl {

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
