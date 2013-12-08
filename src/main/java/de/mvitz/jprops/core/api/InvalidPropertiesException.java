package de.mvitz.jprops.core.api;

@SuppressWarnings("serial")
public final class InvalidPropertiesException extends RuntimeException {

    public InvalidPropertiesException(final String message) {
        super(message);
    }

    public InvalidPropertiesException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
