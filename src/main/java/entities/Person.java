package entities;

import customtype.GenderType;
import enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import org.hibernate.annotations.JavaType;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.TimeZoneColumn;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.java.CharacterArrayJavaType;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Entity(name = "Person")
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Type(value = GenderType.class)
    public Gender gender;

    // this will get mapped to CHAR or NCHAR with a conversion
    @Convert(converter = org.hibernate.type.YesNoConverter.class)
    boolean convertedYesNo;

    // this will get mapped to CHAR or NCHAR with a conversion
    @Convert(converter = org.hibernate.type.TrueFalseConverter.class)
    boolean convertedTrueFalse;

    // this will get mapped to TINYINT with a conversion
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    boolean convertedNumeric;

    @JdbcTypeCode(SqlTypes.DECIMAL)
    private Integer age;

    @Column(columnDefinition = "NUMERIC(10, 4)") // Chỉ định NUMERIC với độ chính xác 10 chữ số, 4 chữ số thập phân
    private Float precisionValue;

    @Column(precision = 15, scale = 2) // Chỉ định NUMERIC(15, 2)
    private BigDecimal amount;

    @Column(length = 255) // Giới hạn độ dài chuỗi là 255 ký tự
    private String nameA;


    @JavaType(CharacterArrayJavaType.class) // Chỉ định ánh xạ `Character[]` thành `VARCHAR`
    private char[] charArray;

    // mapped as CLOB
    @Lob
    char[] primitiveClob;
    @Lob
    Character[] wrapperClob;

    @Lob
    private String largeText;

    @Lob // Chỉ định ánh xạ thành CLOB
    private Clob hugeText;

    @Lob // Ánh xạ thành BLOB
    private Blob binaryData;

    private Duration duration; // Hibernate ánh xạ thành NUMERIC trong cơ sở dữ liệu
    private Instant timestamp; // Hibernate ánh xạ thành TIMESTAMP_UTC

    private LocalDate birthday;

    private LocalTime wakeup;
    private ZoneOffset zoneOffset;

    @JdbcTypeCode(SqlTypes.JSON) // Chỉ định kiểu JSON
    private Map<String, Object> jsonData; // Dữ liệu JSON lưu dưới dạng Map

    @JdbcTypeCode(SqlTypes.SQLXML) // Chỉ định kiểu SQLXML
    private String xmlData; // Dữ liệu XML được lưu dưới dạng chuỗi

    @JdbcTypeCode(SqlTypes.ARRAY) // Ánh xạ mảng Java sang kiểu ARRAY
    @Column(columnDefinition = "text[]") // Chỉ định rõ kiểu dữ liệu PostgreSQL
    private String[] tags;

    @JdbcTypeCode(SqlTypes.ARRAY) // Ánh xạ List<String> sang SQL ARRAY
    @Column(columnDefinition = "text[]") // Chỉ định rõ kiểu dữ liệu PostgreSQL
    private List<String> list;

    @TimeZoneStorage(TimeZoneStorageType.COLUMN) // Lưu múi giờ trong cột riêng biệt
    @TimeZoneColumn(name = "timezone_info") // Tùy chỉnh tên cột cho múi giờ
    @Column(name = "event_time") // Cột lưu thời gian
    private ZonedDateTime eventTime;
}
