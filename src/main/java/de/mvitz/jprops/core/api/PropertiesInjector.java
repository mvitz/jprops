package de.mvitz.jprops.core.api;

import java.lang.reflect.Field;

public final class PropertiesInjector {

    public void injectInto(final Object instance) {
        for (final Field field : instance.getClass().getDeclaredFields()) {
            handle(instance, field);
        }
    }

    private void handle(final Object instance, final Field field) {
        final String value = valueFor(field);
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

    private String valueFor(final Field field) {
        return "foo";
    }

}
