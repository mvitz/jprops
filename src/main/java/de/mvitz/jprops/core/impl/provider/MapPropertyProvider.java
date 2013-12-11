package de.mvitz.jprops.core.impl.provider;

import java.util.HashMap;
import java.util.Map;

import de.mvitz.jprops.core.api.PropertyProvider;

/**
 * {@link PropertyProvider} which is backed by a {@link Map}.
 * 
 * @author Michael Vitz
 * 
 */
public final class MapPropertyProvider implements PropertyProvider {

    private final Map<String, String> map;

    /**
     * Creates a new provider which is backed by the given map.
     * 
     * @param map
     *            the map to use
     */
    public MapPropertyProvider(final Map<String, String> map) {
        this.map = new HashMap<>(map);
    }

    @Override
    public String get(final String key) {
        return map.get(key);
    }

}
