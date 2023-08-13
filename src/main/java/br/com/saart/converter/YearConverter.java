package br.com.saart.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Year;

@Converter(autoApply = true)
public class YearConverter implements AttributeConverter<Year, Short> {

    @Override
    public Short convertToDatabaseColumn(Year year) {
        return year != null ? (short) year.getValue() : null;
    }

    @Override
    public Year convertToEntityAttribute(Short aShort) {
        return aShort != null ? Year.of(aShort) : null;
    }
}
