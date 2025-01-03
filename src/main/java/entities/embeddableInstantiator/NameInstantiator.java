package entities.embeddableInstantiator;

import org.hibernate.metamodel.spi.EmbeddableInstantiator;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.metamodel.spi.ValueAccess;

public class NameInstantiator implements EmbeddableInstantiator {

    @Override
    public Object instantiate(ValueAccess valueAccess, SessionFactoryImplementor sessionFactory) {
        // Lấy giá trị từ cơ sở dữ liệu hoặc nguồn khác
        final String first = valueAccess.getValue(0, String.class);
        final String last = valueAccess.getValue(1, String.class);

        // Tạo một instance của Name
        return new Name(first, last);
    }

    @Override
    public boolean isInstance(Object o, SessionFactoryImplementor sessionFactoryImplementor) {
        return true;
    }

    @Override
    public boolean isSameClass(Object o, SessionFactoryImplementor sessionFactoryImplementor) {
        return true;
    }
}

