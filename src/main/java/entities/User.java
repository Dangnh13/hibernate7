package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

import static org.hibernate.generator.EventType.INSERT;

@Entity
@Data
@Table(name = "`user`") // Sử dụng backtick để thoát
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`select`") // Thoát từ khóa 'select' bằng backtick
    private String select;

    @CurrentTimestamp(event = INSERT)
//@CreationTimestamp(source = SourceType.DB)
    @UpdateTimestamp
    public Instant createdAt; // Lưu thời điểm tạo
}
