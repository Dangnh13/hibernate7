package test_l1;

import entities.embeddablecollection.Address;
import entities.embeddablecollection.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.List;

public class TestEmbeddedableCollection {

    @BeforeAll
    public static void setup() {
        System.out.println("Starting tests...");
    }

    @AfterAll
    public static void teardown() {
        HibernateUtil.shutdown();
        System.out.println("Tests finished.");
    }

    @Test
    public void testCustomTypeGender() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // Tạo một đối tượng Person
        Person person = new Person("John Doe");

// Thêm các địa chỉ
        person.getAddresses().add(new Address("123 Main St", "12345"));
        person.getAddresses().add(new Address("456 Elm St", "67890"));

        session.persist(person);

        tx.commit();
        session.close();

        // Read
        session = HibernateUtil.getSessionFactory().openSession();
        List<Person> list = session.createQuery("from Person", Person.class).list();
        System.out.println(list.size());
        session.close();
    }
}
