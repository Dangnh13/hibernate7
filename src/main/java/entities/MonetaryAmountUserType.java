package entities;

import lombok.Data;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.metamodel.spi.ValueAccess;
import org.hibernate.usertype.CompositeUserType;

import java.io.Serializable;
import java.util.Currency;
import java.util.Objects;

@Data
public class MonetaryAmountUserType implements CompositeUserType<MonetaryAmount> {
    @Override
    public Object getPropertyValue(MonetaryAmount component, int propertyIndex) throws HibernateException {
        if (propertyIndex == 0) {
            return component.getAmount(); // Giá trị số tiền
        } else if (propertyIndex == 1) {
            return component.getCurrency(); // Mã đơn vị tiền tệ
        }
        throw new IllegalArgumentException("Invalid property index: " + propertyIndex);
    }

    @Override
    public MonetaryAmount instantiate(ValueAccess valueAccess, SessionFactoryImplementor sessionFactoryImplementor) {
        Integer amount = valueAccess.getValue(0, Integer.class); // Lấy giá trị cột "amount"
        Currency currency = valueAccess.getValue(1, Currency.class);  // Lấy giá trị cột "currency"
        // Kiểm tra null
        if (amount == null || currency == null) {
            return null; // Nếu giá trị nào đó là null, trả về null
        }
        try {
            return new MonetaryAmount(amount, currency);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid currency code: " + currency.getCurrencyCode(), e);
        }
    }

    @Override
    public Class<?> embeddable() {
        return MonetaryAmount.class;
    }

    @Override
    public Class<MonetaryAmount> returnedClass() {
        return MonetaryAmount.class;
    }

    @Override
    public boolean equals(MonetaryAmount x, MonetaryAmount y) {
        return Objects.equals(x, y);
    }

    @Override
    public int hashCode(MonetaryAmount x) {
        return Objects.hashCode(x);
    }

    @Override
    public MonetaryAmount deepCopy(MonetaryAmount value) {
        if (value == null) {
            return null;
        }
        return new MonetaryAmount(value.getAmount(), value.getCurrency());
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(MonetaryAmount monetaryAmount) {
        if (monetaryAmount == null) {
            return null;
        }
        return new Object[]{
                monetaryAmount.getAmount(),
                monetaryAmount.getCurrency()// Lưu dưới dạng chuỗi
        };
    }


    @Override
    public MonetaryAmount assemble(Serializable cached, Object owner) {
        if (cached == null) {
            return null; // Nếu giá trị cached là null, trả về null
        }
        Object[] cachedValues = (Object[]) cached;
        Integer amount = ((Integer) cachedValues[0]);
        String currencyCode = (String) cachedValues[1];
        Currency currency = Currency.getInstance(currencyCode);
        return new MonetaryAmount(amount, currency);
    }

    @Override
    public MonetaryAmount replace(MonetaryAmount original, MonetaryAmount target, Object owner) {
        return deepCopy(original);
    }
}
