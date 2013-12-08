package de.mvitz.jprops.core.api;

/**
 * This exception is thrown whenever not all properties could be injected successfully.
 * <p>
 * Some of the cases when not all properties could be injected are:
 * <ul>
 * <li>the field the injection takes place is not accessible
 * <li>the value of the property can not be converted into the field's type
 * <li>a required property isn't set
 * 
 * @author Michael Vitz
 * 
 */
@SuppressWarnings("serial")
public final class InvalidPropertiesException extends RuntimeException {

    public InvalidPropertiesException(final String message) {
        super(message);
    }

    public InvalidPropertiesException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
