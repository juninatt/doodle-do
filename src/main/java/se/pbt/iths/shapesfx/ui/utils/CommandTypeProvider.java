package se.pbt.iths.shapesfx.ui.utils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import se.pbt.iths.shapesfx.enums.CommandType;

/**
 * Provides a shared current command type that decides what command will be taken when user clicks on canvas.
 * This class uses JavaFX properties to allow for observable changes.
 */
public class CommandTypeProvider {

    private static final ObjectProperty<CommandType> currentCommandTypeProperty = new SimpleObjectProperty<>(CommandType.EMPTY);

    /**
     * Gets the currently set command type.
     *
     * @return the current command type.
     */
    public static CommandType getCommandType() {
        return currentCommandTypeProperty.get();
    }

    /**
     * Sets the current command type.
     *
     * @param commandType the command type to set.
     */
    public static void setCommandType(CommandType commandType) {
        currentCommandTypeProperty.set(commandType);
    }
}
