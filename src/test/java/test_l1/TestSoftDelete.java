package test_l1;

import entities.softDelete.User;
import entities.softDelete.inheritance.Customer;
import entities.softDelete.inheritance.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;
import util.HibernateUtil;

public class TestSoftDelete {

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
        session.persist(user);
        tx.commit();

        tx = session.beginTransaction();
        session.remove(user);
        tx.commit();
        session.close();

         session = HibernateUtil.getSessionFactory().openSession();
        // Đọc lại  từ DB
        User p2 = session.find(User.class, user.getId());
        Assertions.assertNull(p2);
        session.close();
    }

    @Test
    public void testInheritanceDelete() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Employee emp = new Employee(1L, "Alice", 5000.0);
        Customer cust = new Customer(2L, "Bob", "GOLD");
        session.persist(emp);
        session.persist(cust);
        tx.commit();  // Xem DB => base_person có 2 dòng removed='N'

        // 2) Thực hiện soft-delete Employee
        tx = session.beginTransaction();
        Employee empRef = session.find(Employee.class, 1L);
        session.remove(empRef); // Gọi remove => soft-delete
        tx.commit();

        // Quan sát log SQL:
        // => "UPDATE base_person SET removed=? WHERE id=?"
        // removed='Y'

        // 3) Kiểm tra Customer vẫn removed='N'
        Customer c2 = session.find(Customer.class, 2L);
        // c2 vẫn 'N' => chưa xóa

        session.close();

    }

}
