package test_l1;

import entities.associations.notFound.City;
import entities.associations.notFound.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.Arrays;
import java.util.List;

public class TestAssociationNotFound {

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
        City city = new City();
        city.setName("HN");
        session.persist(city);
        var p =new Person();
        p.setName("DangnH");
        p.setCity(city);
        session.persist(p);
        tx.commit();

        var list = session.createQuery("from Person", Person.class).list();
        System.out.println(list.size());

        session.close();

         session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        city = session.find(City.class, 1l);
        session.remove(city);
        tx.commit();
        list = session.createQuery("from Person", Person.class).list();
        System.out.println(list.size());
        session.close();

    }

}
