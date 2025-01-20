package test_l1;

import entities.associations.oneToMany.unidirectional.Person;
import entities.associations.oneToMany.unidirectional.Phone;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.Arrays;
import java.util.List;

public class TestAssociationOneToMany {

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

//        Khi có "cascade = CascadeType.ALL" thì không cần persion 2 phone tường minh như này
//        session.persist(phone);
//        session.persist(phoneMy);

        Person person = new Person();
        person.setPhones(Arrays.asList(phone, phoneMy));
        session.persist(person);
        tx.commit();
        session.close();

        // Remove
        session = HibernateUtil.getSessionFactory().openSession();
        List<Person> list = session.createQuery("from Person", Person.class).list();
        System.out.println(list.size());

        tx = session.beginTransaction();
        list.get(0).getPhones().remove(phone);
        tx.commit();

        list = session.createQuery("from Person", Person.class).list();
        System.out.println(list.size());
        session.close();
    }

}
