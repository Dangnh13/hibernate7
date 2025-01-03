package test_l1;

import entities.attributeOverride.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.List;

public class TestFomularOnProduct {

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
    public void testProductCRUD() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // Create
        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(3000.00);
        session.persist(product);

        // Create
        Product product2 = new Product();
        product2.setName("Laptop");
        product2.setPrice(1000);
        session.persist(product2);

        tx.commit();
        session.close();

        // Read
        session = HibernateUtil.getSessionFactory().openSession();
        List<Product> products = session.createQuery("from Product", Product.class).list();
        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals("Laptop", products.get(0).getName());
        Assertions.assertEquals(2000, products.get(0).getAveragePrice());
        session.close();
    }
}
