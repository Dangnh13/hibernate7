package test_l1;

import entities.associations.oneToMany.bidirectional.Person;
import entities.associations.oneToMany.bidirectional.Phone;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.Arrays;
import java.util.List;

public class TestAssociationOneToManyBidirectional {

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

        Phone phone = new Phone();
        phone.setNumber("0978534865");

        Phone phoneMy = new Phone();
        phoneMy.setNumber("0399964816");

        Person person = new Person();
        person.addPhone(phone);
        person.addPhone(phoneMy);
        session.persist(person);
        tx.commit();
        session.close();

        // Remove
        session = HibernateUtil.getSessionFactory().openSession();
        List<Person> list = session.createQuery("from Person", Person.class).list();

        List<Phone> phoneList = session.createQuery("from Phone", Phone.class).list();
        System.out.println(list.size());
        session.close();
    }

}
