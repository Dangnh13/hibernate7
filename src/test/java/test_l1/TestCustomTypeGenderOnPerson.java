package test_l1;

import entities.Person;
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

public class TestCustomTypeGenderOnPerson {

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
        Person entity = new Person();
        entity.setName("dang");
        entity.setGender(Gender.MALE);
        entity.setConvertedYesNo(false);
        entity.setConvertedNumeric(false);
        entity.setConvertedTrueFalse(false);
        entity.setAge(100);
        entity.setPrecisionValue(33.33355f);
        entity.setAmount(new BigDecimal(45454.4555555555));
        // Sử dụng ClobProxy để tạo Clob từ chuỗi
        String longText = "This is a very large text content stored as a CLOB.";
        entity.setHugeText(ClobProxy.generateProxy(longText));
        entity.setLargeText(longText);
        entity.setTimestamp(Instant.now()); // Lưu thời điểm hiện tại);
        entity.setWakeup(LocalTime.now());
        entity.setZoneOffset(ZoneOffset.UTC);

        // Lưu dữ liệu JSON dưới dạng Map
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("name", "John Doe");
        jsonData.put("age", 30);
        jsonData.put("active", true);

        entity.setJsonData(jsonData);

        // Lưu dữ liệu XML dưới dạng chuỗi
        String xml = "<person><name>John Doe</name><age>30</age></person>";
        entity.setXmlData(xml);

        entity.setTags(new String[]{"Hibernate", "Java", "SQL"}); // Lưu mảng

        entity.setEventTime(ZonedDateTime.now());
        session.persist(entity);

        tx.commit();
        session.close();

        // Read
        session = HibernateUtil.getSessionFactory().openSession();
        List<Person> list = session.createQuery("from Person", Person.class).list();
        Assertions.assertEquals(Gender.MALE.toString(), list.get(0).getGender().name());
        System.out.println(list.get(0).getTimestamp());
        session.close();
    }
}
