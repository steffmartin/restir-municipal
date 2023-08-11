package br.com.saart.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean, Character> {
    @Override
    public Character convertToDatabaseColumn(Boolean aBoolean) {
        return aBoolean != null ? (aBoolean ? 'S' : 'N') : null;
    }

    @Override
    public Boolean convertToEntityAttribute(Character character) {
        return character != null ? ('S' == character) : null;
    }
}
