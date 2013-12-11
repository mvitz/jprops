package de.mvitz.jprops.core.impl.provider;

import java.io.IOException;
import java.util.Properties;

import de.mvitz.jprops.core.api.PropertyProvider;

/**
 * A {@link PropertyProvider} implementation which uses {@link Properties} as the backend.
 * 
 * @author Michael Vitz
 * 
 */
public final class PropertiesProvider implements PropertyProvider {

    private final Properties properties;

    /**
     * Creates a new provider which is backed by the given properties.
     * 
     * @param properties
     *            the properties to use
     */
    public PropertiesProvider(final Properties properties) {
        this.properties = properties;
    }

    @Override
    public String get(final String key) {
        return properties.getProperty(key);
    }

    /**
     * Creates a {@link PropertiesProvider} backed by a property file.<br>
     * The file is loaded by the given name from the class path.
     * 
     * @param fileName
     *            the properties file's name
     * @return a provider which is backed by the given file
     * @throws IOException
     *             if the file could not be loaded successfully
     */
    public static PropertiesProvider properties(final String fileName) throws IOException {
        final Properties properties = new Properties();
        properties.load(PropertiesProvider.class.getResourceAsStream(fileName));
        return new PropertiesProvider(properties);
    }

    /**
     * Creates a {@link PropertiesProvider} backed by a XML file.<br>
     * The file is loaded by the given name from the class path.
     * 
     * @param fileName
     *            the XML file's name
     * @return a provider which is backed by the given file
     * @throws IOException
     *             if the file could not be loaded successfully
     */
    public static PropertiesProvider xml(final String fileName) throws IOException {
        final Properties properties = new Properties();
        properties.loadFromXML(PropertiesProvider.class.getResourceAsStream(fileName));
        return new PropertiesProvider(properties);
    }

}
