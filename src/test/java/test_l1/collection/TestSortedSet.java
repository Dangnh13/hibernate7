package test_l1.collection;

import entities.collections.sortedSet.Name;
import entities.collections.sortedSet.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

public class TestSortedSet {

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
        Person person = new Person();
        person.setNickname("dangnh");
        person.getNames().add(new Name("A", "B"));
        person.getNames().add(new Name("B", "B"));
        person.getNames().add(new Name("A", "B"));
        person.getNames().add(new Name("A", "C"));
        person.getNames().add(new Name("B", "A"));
        session.persist(person);
        tx.commit();


        session.close();

         session = HibernateUtil.getSessionFactory().openSession();
        // Đọc lại  từ DB
//        Person p2 = session.find(Person.class, person.getId());
        Person p2 = session.byNaturalId(Person.class)
                        .using("nickname", "dangnh")
                                .load();
        System.out.println(p2.toString());
        session.close();
    }

}
