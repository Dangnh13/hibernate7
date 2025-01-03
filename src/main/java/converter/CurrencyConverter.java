package converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Currency;

@Converter(autoApply = true)
public class CurrencyConverter implements AttributeConverter<Currency, String> {

    @Override
    public String convertToDatabaseColumn(Currency currency) {
        // Chuyển `Currency` thành chuỗi mã tiền tệ (ví dụ: USD)
        return currency != null ? currency.getCurrencyCode() : null;
    }

    @Override
    public Currency convertToEntityAttribute(String dbData) {
        // Chuyển chuỗi mã tiền tệ từ DB thành `Currency`
        return dbData != null ? Currency.getInstance(dbData) : null;
    }
}

