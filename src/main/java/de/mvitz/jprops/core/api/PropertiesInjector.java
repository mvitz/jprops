package de.mvitz.jprops.core.api;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.mvitz.jprops.core.impl.typeconverter.BooleanConverter;
import de.mvitz.jprops.core.impl.typeconverter.EnumConverter;
import de.mvitz.jprops.core.impl.typeconverter.IntConverter;
import de.mvitz.jprops.core.impl.typeconverter.StringConverter;
import de.mvitz.jprops.core.spi.ValueTypeConverter;

/**
 * This class is used to inject the properties into an given object.
 * <p>
 * The property's value is retrieved by the given {@link PropertyProvider}.
 * 
 * @author Michael Vitz
 * 
 */
public final class PropertiesInjector {

    private static final Map<Class<?>, ValueTypeConverter<?>> converters = Collections
            .synchronizedMap(new HashMap<Class<?>, ValueTypeConverter<?>>());

    static {
        register(String.class, new StringConverter());
        register(int.class, new IntConverter());
        register(Integer.class, new IntConverter());
        register(boolean.class, new BooleanConverter());
        register(Boolean.class, new BooleanConverter());
        // enum is a special case
    }

    private final PropertyProvider properties;

    /**
     * Creates a new injector with the given {@link PropertyProvider}.
     * 
     * @param provider
     *            the provider used for getting the properties values
     */
    public PropertiesInjector(final PropertyProvider provider) {
        this.properties = provider;
    }

    /**
     * Injects all properties into the given instance.
     * 
     * @param instance
     *            the instance where the properties should be injected into
     * @throws InvalidPropertiesException
     *             if any of the given properties is invalid
     */
    public void injectInto(final Object instance) throws InvalidPropertiesException {
        for (final Field field : instance.getClass().getDeclaredFields()) {
            handle(instance, field);
        }
    }

    private void handle(final Object instance, final Field field) {
        final Object value = valueFor(field);
        try {
            field.set(instance, value);
        } catch (final IllegalAccessException _) {
            field.setAccessible(true);
            try {
                field.set(instance, value);
            } catch (final IllegalAccessException e) {
                throw new InvalidPropertiesException(String.format(
                        "Can't inject field '%s' because: %s", field.getName(),
                        e.getLocalizedMessage()), e);
            }
        }
    }

    private Object valueFor(final Field field) {
        final String value = properties.get(field.getName());
        if (value == null) {
            return null;
        }
        if (field.getType().isEnum()) {
            return convertEnum(field.getType(), value);
        }
        return converter(field.getType()).convert(value);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Object convertEnum(final Class<?> enumType, final String value) {
        return new EnumConverter(enumType).convert(value);
    }

    private ValueTypeConverter<?> converter(final Class<?> type) {
        final ValueTypeConverter<?> converter = converters.get(type);
        if (converter == null) {
            throw new InvalidPropertiesException("No converter found for type: " + type.getName());
        }
        return converter;
    }

    /**
     * Registers a user-defined {@link ValueTypeConverter}.
     * 
     * @param type
     *            the type the given converter converts to
     * @param converter
     *            the converter
     */
    public static <T> void register(final Class<T> type,
            final ValueTypeConverter<? extends T> converter) {
        converters.put(type, converter);
    }

}
