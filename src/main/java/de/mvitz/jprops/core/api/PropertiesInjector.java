package de.mvitz.jprops.core.api;

import java.lang.reflect.Field;

public final class PropertiesInjector {

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
                        "Could not inject field '%s' because: %s", field.getName(),
                        e.getLocalizedMessage()), e);
            }
        }
    }

    private Object valueFor(final Field field) {
        final Class<?> type = field.getType();
        if (int.class.equals(type)) {
            return 0;
        } else if (Integer.class.equals(type)) {
            return 0;
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
