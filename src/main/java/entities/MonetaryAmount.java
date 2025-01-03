package entities;

import converter.CurrencyConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Currency;

@Data
@AllArgsConstructor
@Converter(autoApply = true)
public class MonetaryAmount {

    private Integer amount;

    @Convert(converter = CurrencyConverter.class)
    private Currency currency;
}
