package test_l1;

import entities.customSequenceGenerator.Book;
import entities.derivedIdentifiers.Person;
import entities.derivedIdentifiers.PersonDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.List;

public class TestDerivedIdentifiers {

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
    public void testAssociationOverride() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Person p = new Person();
        p.setId(1l);
        p.setRegistrationNumber("Reg1111");

        session.persist(p);

        PersonDetails personDetails = new PersonDetails();


        // Gán giá trị cho idPrimary cũng vô nghĩa vì nó lấy Id của Person để gán
//        personDetails.setIdPrimary(2l);

        personDetails.setNickName("NickName@gmail");
        personDetails.setPerson(p);
        session.persist(personDetails);
        tx.commit();

        // Read
        session = HibernateUtil.getSessionFactory().openSession();
        List<PersonDetails> list = session.createQuery("from PersonDetails", PersonDetails.class).list();
        System.out.println(list.size());
        session.close();
    }

}
