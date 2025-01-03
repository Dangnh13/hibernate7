package test_l1;

import entities.embeddedableMapKey.Location;
import entities.embeddedableMapKey.TouristGuide;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.List;

public class TestEmbeddedableMapKey {

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
        TouristGuide guide = new TouristGuide("John Doe");

        // Thêm các mục vào Map
        guide.getLocationDetails().put(new Location("USA", "New York"), "Statue of Liberty");
        guide.getLocationDetails().put(new Location("USA", "New York"), "Hai Dang");
        guide.getLocationDetails().put(new Location("France", "Paris"), "Eiffel Tower");

        session.persist(guide);

        tx.commit();
        session.close();

        // Read
        session = HibernateUtil.getSessionFactory().openSession();
        List<TouristGuide> list = session.createQuery("from TouristGuide", TouristGuide.class).list();
        System.out.println(list.size());
        session.close();
    }
}
