package test_l1;

import entities.targetMapping.City;
import entities.targetMapping.GPSImplement;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.List;

public class TestTarget {

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
    public void testTarget() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // Tạo một thành phố
        City city = new City();
        city.setName("Cluj");
        city.setCoordinates(new GPSImplement(46.77120, 23.62360));

        session.persist(city);

        tx.commit();
        session.close();

        // Read
        session = HibernateUtil.getSessionFactory().openSession();
        List<City> list = session.createQuery("from City", City.class).list();
        System.out.println(list.size());
        session.close();
    }
}
