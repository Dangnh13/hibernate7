package test_l1;

import entities.sequenceGeneratorAnnotation.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.List;

public class TestSequenceGeneratorAnnotation {

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
        Product p = new Product();
        p.setName("ngo hai dang");

        session.persist(p);

        session.persist(p);

        Product p2 = new Product();
        p2.setName("nguyen van A");

        session.persist(p2);

        tx.commit();

        session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list = session.createQuery("from Product", Product.class).list();
        System.out.println(list.size());

        session.close();
    }

}
