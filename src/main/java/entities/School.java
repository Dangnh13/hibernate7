package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;

import java.net.InetAddress;
import java.util.UUID;

@Entity
@Data
public class School {
    @Id
    @JdbcTypeCode(java.sql.Types.BINARY) // Lưu UUID dưới dạng BINARY
    private UUID id; // Hibernate tự động ánh xạ UUID thành kiểu phù hợp với cơ sở dữ liệu

    private String uname;
    private InetAddress address;

}
