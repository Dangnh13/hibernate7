package test_l1;

import entities.embeddableIdentifier.Order;
import entities.embeddedId.withManytoOne.PK;
import entities.embeddedId.withManytoOne.Subsystem;
import entities.embeddedId.withManytoOne.SystemUser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.List;

public class TestEmbeddedIdWithManytoOne {

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
        Subsystem subsystem = new Subsystem();
        subsystem.setDescription("description");

        session.persist(subsystem);

        PK pk = new PK(subsystem, "Name");

        SystemUser systemUser = new SystemUser();
        systemUser.setName("System name");
        systemUser.setPk(pk);
        session.persist(systemUser);

        tx.commit();
        session.close();

        // Read
        session = HibernateUtil.getSessionFactory().openSession();
        List<SystemUser> list = session.createQuery("from SystemUser", SystemUser.class).list();
        System.out.println(list.size());
        session.close();
    }
}
