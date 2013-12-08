package de.mvitz.jprops.core.impl.typeconverter;

import java.util.Arrays;

import de.mvitz.jprops.core.api.InvalidPropertiesException;
import de.mvitz.jprops.core.spi.ValueTypeConverter;

public class EnumConverter<T extends Enum<T>> implements ValueTypeConverter<T> {

    private final Class<T> enumType;

    public EnumConverter(final Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public T convert(final String value) {
        final String s = value.replaceAll("-", "_").toUpperCase();
        T convertedValue = null;
        for (final T o : enumType.getEnumConstants()) {
            if (o.name().equalsIgnoreCase(s)) {
                convertedValue = o;
                break;
            }
        }
        if (convertedValue == null) {
            throw new InvalidPropertiesException(String.format(
                    "Illegal enum value '%s'. Needs to be one of: %s", value,
                    Arrays.asList(enumType.getEnumConstants())));
        }
        return convertedValue;
    }

}
