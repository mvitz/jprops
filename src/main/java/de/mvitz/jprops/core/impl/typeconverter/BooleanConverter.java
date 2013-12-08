package de.mvitz.jprops.core.impl.typeconverter;

import java.util.Arrays;
import java.util.List;

import de.mvitz.jprops.core.api.InvalidPropertiesException;
import de.mvitz.jprops.core.spi.ValueTypeConverter;

public final class BooleanConverter implements ValueTypeConverter<Boolean> {

    private static final List<String> ACCEPTABLE_VALUES = Arrays.asList("true", "on", "yes", "1",
            "false", "off", "no", "0");

    @Override
    public Boolean convert(final String value) {
        final int index = ACCEPTABLE_VALUES.indexOf(value.toLowerCase());
        if (index == -1) {
            throw new InvalidPropertiesException(String.format(
                    "Illegal boolean value '%s'. Needs to be one of: %s", value, ACCEPTABLE_VALUES));
        }
        return index < (ACCEPTABLE_VALUES.size() / 2);
    }

}
