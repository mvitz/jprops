package de.mvitz.jprops.core.api;

import java.lang.reflect.Field;

public final class PropertiesInjector {

    private final PropertyProvider properties;

    public PropertiesInjector(final PropertyProvider provider) {
        this.properties = provider;
    }

    public void injectInto(final Object instance) {
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
        final Class<?> type = field.getType();
        final Object value = properties.get(field.getName());
        if (value == null && type.isPrimitive()) {
            throw new InvalidPropertiesException(
                    "Can't inject 'null' into primitive field of type: " + type.getName());
        }
        if (int.class.equals(type) || Integer.class.equals(type)) {
            return Integer.parseInt(value.toString());
        } else if (boolean.class.equals(type)) {
            return false;
        } else if (Boolean.class.equals(type)) {
            return false;
        } else if (type.isEnum()) {
            for (final Object c : type.getEnumConstants()) {
                return c;
            }
        }
        return "foo";
    }

}
