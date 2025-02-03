package test_l1;

import entities.associations.anyAnnotation.CardPayment;
import entities.associations.anyAnnotation.CheckPayment;
import entities.associations.anyAnnotation.PaymentHolder;
import entities.associations.manyToMany.unidirectional.Address;
import entities.associations.manyToMany.unidirectional.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.List;

public class TestAssociationAnyAnnotation {

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
        // 1) Tạo / lưu Payment
        CardPayment cardPayment = new CardPayment(100L, "CARD-12345", "Alice");
        session.persist(cardPayment);
        CheckPayment checkPayment = new CheckPayment(200L, "CHK-999", "LocalBank");
        session.persist(checkPayment);

        // 2) Tạo PaymentHolder trỏ đến cardPayment
        PaymentHolder ph1 = new PaymentHolder("Holder A", cardPayment);
        session.persist(ph1);

        // 3) Tạo PaymentHolder trỏ đến checkPayment
        PaymentHolder ph2 = new PaymentHolder("Holder B", checkPayment);
        session.persist(ph2);
        tx.commit();

        // 4) Đọc lại PaymentHolder từ DB
        PaymentHolder reloaded1 = session.find(PaymentHolder.class, ph1.getId());
        PaymentHolder reloaded2 = session.find(PaymentHolder.class, ph2.getId());

        System.out.println("reloaded1 => " + reloaded1.getHolderName()
                + " / Payment Info = " + reloaded1.getPayment().getPaymentInfo());

        System.out.println("reloaded2 => " + reloaded2.getHolderName()
                + " / Payment Info = " + reloaded2.getPayment().getPaymentInfo());

        // Kết quả in ra:
        // reloaded1 => Holder A / Payment Info = CardPayment: CARD-12345 / Alice
        // reloaded2 => Holder B / Payment Info = CheckPayment: CHK-999 / LocalBank

        session.close();
    }

}
