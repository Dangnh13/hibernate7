package test_l1;

import entities.embeddedCollectionInCollection.ContactInfo;
import entities.embeddedCollectionInCollection.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.Arrays;
import java.util.List;

public class TestEmbeddedCollectionInCollection {

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

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setPhoneNumber("0978534865");
        contactInfo.setEmails(Arrays.asList("a@gmail.com", "b@yahoo.com"));

        Person p = new Person();
        p.setContactInfo(contactInfo);
        p.setName("Nguyen van A");

        session.persist(p);
        tx.commit();
        session.close();

        // Read
        session = HibernateUtil.getSessionFactory().openSession();
        List<Person> list = session.createQuery("from Person", Person.class).list();
        System.out.println(list.size());
        session.close();
    }

}
