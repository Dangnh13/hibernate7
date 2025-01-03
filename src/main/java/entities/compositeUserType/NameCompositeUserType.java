package entities.compositeUserType;

import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.metamodel.spi.ValueAccess;
import org.hibernate.usertype.CompositeUserType;

import java.io.Serializable;
import java.util.Objects;

public class NameCompositeUserType implements CompositeUserType<Name> {

    public static class NameMapper {
        String firstName;
        String lastName;
    }

    @Override
    public Class<?> embeddable() {
        return NameMapper.class; // Mapper class đại diện cho cấu trúc ánh xạ
    }

    @Override
    public Class<Name> returnedClass() {
        return Name.class; // Kiểu dữ liệu trả về
    }

    @Override
    public Name instantiate(ValueAccess valueAccess, SessionFactoryImplementor sessionFactory) {
        // Khởi tạo Name từ cột trong cơ sở dữ liệu
        String first = valueAccess.getValue(0, String.class);
        String last = valueAccess.getValue(1, String.class);
        return new Name(first, last);
    }

    @Override
    public Object getPropertyValue(Name component, int property) {
        // Truy xuất giá trị của các thuộc tính
        if (property == 0) return component.firstName();
        if (property == 1) return component.lastName();
        return null;
    }

    @Override
    public boolean equals(Name x, Name y) {
        return x == y || (x != null && y != null &&
                x.firstName().equals(y.firstName()) && x.lastName().equals(y.lastName()));
    }

    @Override
    public int hashCode(Name x) {
        return Objects.hash(x.firstName(), x.lastName());
    }

    @Override
    public Name deepCopy(Name value) {
        return value; // Lớp bất biến, trả về chính nó
    }

    @Override
    public boolean isMutable() {
        return false; // Lớp bất biến
    }

    @Override
    public Serializable disassemble(Name value) {
        return new String[]{value.firstName(), value.lastName()};
    }

    @Override
    public Name assemble(Serializable cached, Object owner) {
        String[] parts = (String[]) cached;
        return new Name(parts[0], parts[1]);
    }

    @Override
    public Name replace(Name detached, Name managed, Object owner) {
        return detached;
    }
}

