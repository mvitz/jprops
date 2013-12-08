package de.mvitz.jprops.core.api;

/**
 * {@link PropertyProvider} is the key abstraction for getting the value for a property name.
 * 
 * @author Michael Vitz
 * 
 */
public interface PropertyProvider {

    /**
     * Gets the value for the property identified by the given key.
     * 
     * @param key
     *            the key of the property the value should be gotten for
     * @return the value of the property, <code>null</code> if no value was found
     */
    Object get(String key);

}
