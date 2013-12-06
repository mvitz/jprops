package de.mvitz.jprops.api;

import java.lang.reflect.Field;

public final class Properties {

    public static <T> T create(final T instance) {
        for (final Field field : instance.getClass().getDeclaredFields()) {
            handle(instance, field);
        }
        return instance;
    }

    private static <T> void handle(final T instance, final Field field) {
        final boolean accessible = field.isAccessible();
        if (!accessible) {
            field.setAccessible(true);
        }
        try {
            field.set(instance, "foo");
            if (!accessible) {
                field.setAccessible(false);
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException("Could not inject into field: " + field.getName(), e);
        }
    }

}
