package util;


import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
//            sessionFactory = new Configuration().configure("hibernate_pg_PhysicalNamingStrategy.cfg.xml").buildSessionFactory();
//            sessionFactory = new Configuration().configure("hibernate_pg.cfg.xml").buildSessionFactory();
            sessionFactory = new Configuration().configure("hibernate_pg_collection.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static EntityManagerFactory createEntityManagerFactory() {
        // Load hibernate.cfg.xml
        Configuration configuration = new Configuration();
        configuration.configure("hibernate_pg.cfg.xml");

        // Lấy các thuộc tính từ Configuration
        Properties properties = configuration.getProperties();

        // Tạo EntityManagerFactory từ HibernatePersistenceProvider
        return new HibernatePersistenceProvider()
                .createEntityManagerFactory("default", properties);
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
