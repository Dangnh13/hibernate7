package test_l1;

import entities.struct.EmbeddableAggregate;
import entities.struct.StructHolder;
import entities.targetMapping.City;
import entities.targetMapping.GPSImplement;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.List;

public class TestStruct {

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
        var struct = new EmbeddableAggregate();
        struct.setAddress("HN");
        struct.setAge(11);
        StructHolder structHolder = new StructHolder();
        structHolder.setAggregate(struct);
        structHolder.setUname("ngo ha idang");
        structHolder.setAggregateJson(struct);
        session.persist(structHolder);

        tx.commit();
        session.close();

        // Read
        session = HibernateUtil.getSessionFactory().openSession();
        List<StructHolder> list = session.createQuery("from StructHolder", StructHolder.class).list();
        System.out.println(list.size());
        session.close();
    }
}
