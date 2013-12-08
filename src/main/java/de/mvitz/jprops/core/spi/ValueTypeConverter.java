package de.mvitz.jprops.core.spi;

/**
 * Converts a value to the specified type.
 * 
 * @author Michael Vitz
 * 
 * @param <T>
 *            the type the value will be converted to
 */
public interface ValueTypeConverter<T> {

    /**
     * Converts the given value.
     * 
     * @param value
     *            the value to convert
     * @return the converted value
     */
    T convert(String value);

}
