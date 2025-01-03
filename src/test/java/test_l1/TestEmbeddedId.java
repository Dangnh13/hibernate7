package test_l1;

import entities.embeddableIdentifier.Order;
import entities.embeddableIdentifier.OrderId;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.List;

public class TestEmbeddedId {

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

        // Tạo OrderId
        OrderId orderId = new OrderId(1L, 101L);

// Tạo Order
        Order order = new Order(orderId, 5);
        session.persist(order);

        tx.commit();
        session.close();

        // Read
        session = HibernateUtil.getSessionFactory().openSession();
        List<Order> list = session.createQuery("from Order", Order.class).list();
        System.out.println(list.size());
        session.close();
    }
}
