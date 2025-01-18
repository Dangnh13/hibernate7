package test_l1;

import entities.unnamedTableGenerator.People;
import entities.unnamedTableGenerator.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.List;

public class TestUnnamedTableGenerator {
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
    public void testWithoutTableGeneratorAnnotation() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        // Tạo đối tượng Country
        Product p = new Product();
        p.setName("ngo hai dang");

        session.persist(p);

        tx.commit();

        session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list = session.createQuery("from Product", Product.class).list();
        System.out.println(list.size());

        session.close();
    }

    @Test
    public void testWithTableGeneratorAnnotation() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        // Tạo đối tượng People
        for (int i = 0; i < 3; i++) {
            People p = new People();
            p.setName(i+ " - ngo hai dang - " + i);

            session.persist(p);
        }


        tx.commit();

        session = HibernateUtil.getSessionFactory().openSession();
        List<People> list = session.createQuery("from People", People.class).list();
        System.out.println(list.size());

        session.close();
    }
}
