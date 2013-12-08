package de.mvitz.jprops.core.impl.typeconverter;

import de.mvitz.jprops.core.spi.ValueTypeConverter;

public final class IntConverter implements ValueTypeConverter<Integer> {

    @Override
    public Integer convert(final String value) {
        return Integer.parseInt(value);
    }

}
