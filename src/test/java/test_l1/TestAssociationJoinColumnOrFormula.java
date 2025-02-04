package test_l1;

import entities.associations.joinColumnOrFormula.Country;
import entities.associations.joinColumnOrFormula.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

public class TestAssociationJoinColumnOrFormula {

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
        US.set_default(true);
        US.setPrimaryLanguage("English");
        US.setName("United States");

        Country Romania = new Country();
        Romania.setId(40);
        Romania.set_default(false);
        Romania.setName("Romania");
        Romania.setPrimaryLanguage("Romanian");

        session.persist(US);
        session.persist(Romania);

        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setLanguage("English");
        session.persist(user1);

        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Vlad");
        user2.setLastName("Mihalcea");
        user2.setLanguage("Romanian");
        session.persist(user2);

        tx.commit();
        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        User u1 = session.find(User.class, user1.getId());
        User u2 = session.find(User.class, user2.getId());
        System.out.println("u1:" + u1.getCountry().getName());
        System.out.println("u2:" + u2.getCountry().getName());
        session.close();

        // u1: User(id=1, firstName=John, lastName=Doe, language=English,
        // country=Country(id=1, name=United States, primaryLanguage=English, _default=true))

        //u2: User(id=2, firstName=Vlad, lastName=Mihalcea, language=Romanian, country=null)
    }

}
