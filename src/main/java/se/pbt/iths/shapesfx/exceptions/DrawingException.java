package se.pbt.iths.shapesfx.exceptions;

public class DrawingException extends Exception {

    public DrawingException() {
        super();
    }

    public DrawingException(String message) {
        super(message);
    }

    public DrawingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DrawingException(Throwable cause) {
        super(cause);
    }
}

