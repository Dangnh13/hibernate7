package test_l1.collection;

import entities.associations.anyAnnotation.CardPayment;
import entities.associations.anyAnnotation.CheckPayment;
import entities.associations.anyAnnotation.PaymentHolder;
import entities.collections.mappingLists.Address;
import entities.collections.mappingLists.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

public class TestMappingList {

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
        Person person = new Person("Alice");
        person.getAddresses().add(new Address("New York", "5th Avenue"));
        person.getAddresses().add(new Address("Los Angeles", "Sunset Blvd"));
        person.getAddresses().add(new Address("Chicago", "Michigan Ave"));
        session.persist(person);
        tx.commit();


        session.close();

         session = HibernateUtil.getSessionFactory().openSession();
        // Đọc lại  từ DB
        Person p2 = session.find(Person.class, person.getId());

        System.out.println(p2.toString());
        session.close();
    }

}
