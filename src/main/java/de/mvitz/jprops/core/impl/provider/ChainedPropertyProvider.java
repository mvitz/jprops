package de.mvitz.jprops.core.impl.provider;

import java.util.Arrays;
import java.util.List;

import de.mvitz.jprops.core.api.PropertyProvider;

/**
 * A {@link PropertyProvider} which iterates over all given {@link PropertyProvider providers} and
 * returns first found value.
 * 
 * @author Michael Vitz
 * 
 */
public final class ChainedPropertyProvider implements PropertyProvider {

    private final List<PropertyProvider> providers;

    /**
     * Creates a new provider which uses the given chain.
     * 
     * @param providers
     *            the providers to use
     */
    public ChainedPropertyProvider(final PropertyProvider... providers) {
        this.providers = Arrays.asList(providers);
    }

    @Override
    public String get(final String key) {
        for (final PropertyProvider provider : providers) {
            final String value = provider.get(key);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

}
