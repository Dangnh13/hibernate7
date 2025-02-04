package test_l1;

import entities.associations.anyAnnotation.CardPayment;
import entities.associations.anyAnnotation.CheckPayment;
import entities.associations.anyAnnotation.PaymentHolder;
import entities.associations.joinFormula.Country;
import entities.associations.joinFormula.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

public class TestAssociationJoinFormula {

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
        Country US = new Country();
        US.setId(1);
        US.setName("United States");

        Country Romania = new Country();
        Romania.setId(40);
        Romania.setName("Romania");

        session.persist(US);
        session.persist(Romania);

        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setPhoneNumber("+1-234-5678");
        session.persist(user1);

        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Vlad");
        user2.setLastName("Mihalcea");
        user2.setPhoneNumber("+40-123-4567");
        session.persist(user2);

        tx.commit();
        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        User u1 = session.find(User.class, user1.getId());
        System.out.println("u1:" + u1.getCountry().getName());
        session.close();
    }

}
