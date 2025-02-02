package test_l1;

import entities.associations.manyToMany.unidirectional.Address;
import entities.associations.manyToMany.unidirectional.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.Arrays;
import java.util.List;

public class TestAssociationManyToMany {

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

        Address a1 = new Address();
        a1.setNumber("To1");
        a1.setStreet("ss1");

        Address a2 = new Address();
        a2.setNumber("To2");
        a2.setStreet("ss2");

        Person person = new Person();
        person.getAddresses().add(a1);
        person.getAddresses().add(a2);
        session.persist(person);
        tx.commit();
        session.close();

        // Remove
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        List<Person> list = session.createQuery("from Person", Person.class).list();
        person = list.get(0);
        person.getAddresses().remove(a1);
        tx.commit();

        list = session.createQuery("from Person", Person.class).list();
        System.out.println(list.size());
        session.close();
    }

    @Test
    public void testBidirectional() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        var a1 = new  entities.associations.manyToMany.bidirectional.Address();
        a1.setNumber("To1");
        a1.setStreet("ss1");

        var a2 = new entities.associations.manyToMany.bidirectional.Address();
        a2.setNumber("To2");
        a2.setStreet("ss2");

        var person = new entities.associations.manyToMany.bidirectional.Person();
        person.addAddress(a1);
        person.addAddress(a2);
        session.persist(person);
        tx.commit();
        session.close();

        // Remove
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        var list = session.createQuery("from Person", entities.associations.manyToMany.bidirectional.Person.class).list();
        person = list.get(0);
        person.removeAddress(a1);
        tx.commit();

        list = session.createQuery("from Person", entities.associations.manyToMany.bidirectional.Person.class).list();
        System.out.println(list.size());
        session.close();
    }

    @Test
    public void testBidirectionalLinkEntity() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        var a1 = new  entities.associations.manyToMany.bidirectional.linkEntity.Address();

        var a2 = new entities.associations.manyToMany.bidirectional.linkEntity.Address();
        session.persist(a1);
        session.persist(a2);
        var person = new entities.associations.manyToMany.bidirectional.linkEntity.Person();
        person.addAddress(a1);
        person.addAddress(a2);
        session.persist(person);
        tx.commit();
        session.close();

        // Remove
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        var list = session.createQuery("from Person", entities.associations.manyToMany.bidirectional.linkEntity.Person.class).list();
        person = list.get(0);
        person.removeAddress(person.getPersonAddresses().get(0).getAddress());

        tx.commit();

        list = session.createQuery("from Person", entities.associations.manyToMany.bidirectional.linkEntity.Person.class).list();
        System.out.println(list.size());
        session.close();
    }
}
