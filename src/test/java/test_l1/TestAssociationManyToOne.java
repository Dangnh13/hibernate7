package test_l1;

import entities.Savings;
import entities.associationOverride.Book;
import entities.associationOverride.Country;
import entities.associationOverride.Publisher;
import entities.associations.manyToOne.Person;
import entities.associations.manyToOne.Phone;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.List;

public class TestAssociationManyToOne {

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

        Person person = new Person();
        person.setUname("Ngo hai dang");
        session.persist(person);

        Phone phone = new Phone();
        phone.setNumber("0978534865");
        phone.setPerson(person);
        session.persist(phone);

        Phone phoneMy = new Phone();
        phoneMy.setNumber("0399964816");
        phoneMy.setPerson(person);
        session.persist(phoneMy);

        tx.commit();

        // Read
        session = HibernateUtil.getSessionFactory().openSession();
        List<Phone> list = session.createQuery("from Phone", Phone.class).list();
        System.out.println(list.size());
        session.close();
    }

}
