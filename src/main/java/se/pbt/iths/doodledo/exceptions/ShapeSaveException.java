package se.pbt.iths.doodledo.exceptions;

/**
 * This exception is thrown when there's a problem saving a shape.
 */
public class ShapeSaveException extends Exception {

    /**
     * Default constructor.
     */
    public ShapeSaveException() {
        super();
    }

    /**
     * Constructor that allows a specific error message to be specified.
     *
     * @param message The detailed error message.
     */
    public ShapeSaveException(String message) {
        super(message);
    }

    /**
     * Constructor that allows a specific cause to be set.
     *
     * @param cause The underlying cause of this exception.
     */
    public ShapeSaveException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor that allows both a specific error message and a cause to be set.
     *
     * @param message The detailed error message.
     * @param cause   The underlying cause of this exception.
     */
    public ShapeSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
