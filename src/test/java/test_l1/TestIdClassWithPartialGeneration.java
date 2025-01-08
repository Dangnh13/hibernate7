package test_l1;

import entities.idClassWithPartialGeneration.SystemUser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.List;

public class TestIdClassWithPartialGeneration {

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
        SystemUser systemUser = new SystemUser();
        systemUser.setSubsystem("subSystem");
        systemUser.setUsername("Ngo hai dang");
        systemUser.setName("Nameee");
        session.persist(systemUser);

        tx.commit();

        session = HibernateUtil.getSessionFactory().openSession();
        List<SystemUser> list = session.createQuery("from SystemUser", SystemUser.class).list();
        System.out.println(list.size());

        session.close();
    }

}
