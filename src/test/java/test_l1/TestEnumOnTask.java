package test_l1;

import entities.Task;
import enums.Status;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.List;

public class TestEnumOnTask {

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
    public void testEnumTypeIsString() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // Create
        Task task = new Task();
        task.setStatus(Status.ACTIVE);
        session.persist(task);

        tx.commit();
        session.close();

        // Read
        session = HibernateUtil.getSessionFactory().openSession();
        List<Task> list = session.createQuery("from Task", Task.class).list();
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(Status.ACTIVE.toString(), list.get(0).getStatus().name());
        session.close();
    }
}
