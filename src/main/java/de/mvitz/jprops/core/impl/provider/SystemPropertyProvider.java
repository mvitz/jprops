package de.mvitz.jprops.core.impl.provider;

import de.mvitz.jprops.core.api.PropertyProvider;

/**
 * A {@link PropertyProvider} which uses the system properties as backend.
 * 
 * @author Michael Vitz
 * 
 */
public final class SystemPropertyProvider implements PropertyProvider {

    private final String prefix;

    /**
     * Creates a new provider.
     */
    public SystemPropertyProvider() {
        this(null);
    }

    /**
     * Creates a new provider which uses the given prefix.
     * 
     * @param prefix
     *            the prefix to use when looking up a property
     */
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
