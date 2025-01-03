package test_l1;

import entities.embeddableInstantiator.Name;
import entities.embeddableInstantiator.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class TestEmbeddableInstantiator {

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
    public void test() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        // Create
        Name name = new Name("John", "Doe");
        Person person = new Person();
        person.setName(name);
        person.setAliases(Set.of(new Name("mot", "hai"), new Name("ba", "bon")));
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
