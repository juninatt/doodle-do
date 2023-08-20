package se.pbt.iths.shapesfx.exceptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ShapeSaveException tests:")
public class ShapeSaveExceptionTest {

    @Test
    @DisplayName("ShapeSaveException is created with no message and no cause")
    public void testDefaultConstructor() {
        var exception = new ShapeSaveException();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    @DisplayName("ShapeSaveException is created with the correct message and no cause")
    public void testMessageConstructor() {
        var message = "This is a test message";
        var exception = new ShapeSaveException(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    @DisplayName("ShapeSaveException is created with the correct cause and message from the cause")
    public void testCauseConstructor() {
        var cause = new RuntimeException("This is a test cause");
        var exception = new ShapeSaveException(cause);
        assertEquals(cause.toString(), exception.getMessage());
        assertSame(cause, exception.getCause());
    }

    @Test
    @DisplayName("ShapeSaveException is created with the correct message and cause")
    public void testMessageAndCauseConstructor() {
        var message = "This is another test message";
        var cause = new RuntimeException("This is another test cause");
        var exception = new ShapeSaveException(message, cause);
        assertEquals(message, exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}
