package test_l1;

import entities.associations.oneToOne.unidirectional.Phone;
import entities.associations.oneToOne.unidirectional.PhoneDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.Arrays;
import java.util.List;

public class TestAssociationOneToOne {

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
    public void testUnidirectional() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        PhoneDetails phoneDetails = new PhoneDetails();
        phoneDetails.setProvider("SamSung");
        phoneDetails.setTechnology("HN");
//        session.persist(phoneDetails);
        Phone phone = new Phone();
        phone.setNumber("0978534865");
        phone.setPhoneDetails(phoneDetails);

        session.persist(phone);
        tx.commit();
        session.close();

        // Remove
        session = HibernateUtil.getSessionFactory().openSession();
        List<Phone> list = session.createQuery("from Phone", Phone.class).list();
        System.out.println(list.size());
        session.close();
    }

    @Test
    public void testBidirectional() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        var phoneDetails = new entities.associations.oneToOne.bidirectional.PhoneDetails();
        phoneDetails.setProvider("SamSung");
        phoneDetails.setTechnology("HN");

        var phone = new entities.associations.oneToOne.bidirectional.Phone();
        phone.setNumber("0978534865");
        phone.addDetails(phoneDetails);
        session.persist(phone);
        tx.commit();
        session.close();

        // Remove
        session = HibernateUtil.getSessionFactory().openSession();
        List<entities.associations.oneToOne.bidirectional.Phone> list = session.createQuery("from Phone", entities.associations.oneToOne.bidirectional.Phone.class).list();
        System.out.println(list.size());
        session.close();
    }
}
