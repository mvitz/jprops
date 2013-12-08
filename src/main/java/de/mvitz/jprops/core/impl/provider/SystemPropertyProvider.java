package de.mvitz.jprops.core.impl.provider;

import de.mvitz.jprops.core.api.PropertyProvider;

public final class SystemPropertyProvider implements PropertyProvider {

    private final String prefix;

    public SystemPropertyProvider() {
        this(null);
    }

    public SystemPropertyProvider(final String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String get(final String key) {
        return System.getProperty(key(key));
    }

    private String key(final String key) {
        return prefix == null ? key : prefix + key;
    }

}
