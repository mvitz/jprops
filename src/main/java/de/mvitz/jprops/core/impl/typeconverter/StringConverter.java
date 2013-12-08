package de.mvitz.jprops.core.impl.typeconverter;

import de.mvitz.jprops.core.spi.ValueTypeConverter;

public final class StringConverter implements ValueTypeConverter<String> {

    @Override
    public String convert(final String value) {
        return value;
    }

}
