package test_l1;

import entities.Person;
import entities.User;
import enums.Gender;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.proxy.ClobProxy;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestGeneratePropertiesOnUser {

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

        // Create
        User entity = new User();
        entity.setSelect("My select");
        session.persist(entity);

        tx.commit();
        session.close();

        // Read
        session = HibernateUtil.getSessionFactory().openSession();
        List<User> list = session.createQuery("from User", User.class).list();
        System.out.println(list.get(0).getCreatedAt());
        session.close();
    }
}
