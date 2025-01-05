package test_l1;

import entities.problemHashCodeAndEquals.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEqualAndHashcode {

    @Test
    public void testGeneratedIdWithSet() {
        Set<Book> bookSet = new HashSet<>();

        // Tạo đối tượng Book mà chưa được lưu vào database
        Book book1 = new Book();
        book1.setTitle("High-Performance Java Persistence");

        Book book2 = new Book();
        book2.setTitle("Java Persistence with Hibernate");

        // Thêm vào Set
        bookSet.add(book1);
        bookSet.add(book2);

        // Kiểm tra số lượng phần tử trong Set
        assertEquals(1, bookSet.size()); // Kết quả: 1, lưu book1 vì trùng ID = null
        assertTrue(bookSet.contains(book1)); // Kết quả: true

        // Lưu vào database
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(book1);
        session.persist(book2);

        tx.commit();
        session.close();

        System.out.println("After persist and flush:");
        for (Book book : bookSet) {
            System.out.println("Book title: " + book.getTitle() + ", ID: " + book.getId());
        }

        // Kiểm tra lại Set sau khi ID được sinh
        assertFalse(bookSet.contains(book1)); // Kết quả: Vì ID sinh ra nên hashcode sẽ khác => Bookset sẽ không còn chứa book1
    }
}
