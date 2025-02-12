package test_l1;

import entities.collections.mappingLists.Address;
import entities.collections.mappingLists.Person;
import entities.partition.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

public class TestPartition {

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
        var user = new User();
        user.setFirstname("dang");
        user.setLastname("ngo");
        user.setTenantKey("MyKey");
        session.persist(user);
        tx.commit();

        tx = session.beginTransaction();
        user.setLastname("nguyen");
        tx.commit();
        session.close();

         session = HibernateUtil.getSessionFactory().openSession();
        // Đọc lại  từ DB
        User p2 = session.find(User.class, user.getId());

        System.out.println(p2.toString());
        session.close();
    }

}
