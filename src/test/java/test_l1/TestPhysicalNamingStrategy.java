package test_l1;

import entities.associationOverride.Book;
import entities.associationOverride.Country;
import entities.associationOverride.Publisher;
import entities.attributeOverride.Product;
import entities.physicalNamingStrategy.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.List;

public class TestPhysicalNamingStrategy {

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
        Person p = new Person();
        p.setUserName("ngo hai dang");
        p.setStreetCode(123456);
        p.setAddress("Ha noi");
        session.persist(p);

        Person p2 = new Person();
        p2.setUserName("nguyen ngoc my");
        p2.setStreetCode(123456);
        p2.setAddress("Ha noi");
        session.persist(p2);
        tx.commit();

        tx = session.beginTransaction();
        p2.setStreetCode(44444);
        session.persist(p2);
        tx.commit();
        session.close();

        // Read
        session = HibernateUtil.getSessionFactory().openSession();
        List<Person> products = session.createQuery("from Person", Person.class).list();
        Assertions.assertEquals(2, products.size());
        session.close();
    }

}
