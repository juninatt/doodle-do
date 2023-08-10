package se.pbt.iths.shapesfx.ui.utils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import se.pbt.iths.shapesfx.enums.ActionType;

/**
 * Provides a shared current action type that decides that action will be taken when user clicks on canvas.
 * This class uses JavaFX properties to allow for observable changes.
 */
public class ActionTypeProvider {

    private static final ObjectProperty<ActionType> currentActionProperty = new SimpleObjectProperty<>(ActionType.EMPTY);

    /**
     * Gets the currently set action type.
     *
     * @return the current action type.
     */
    public static ActionType getType() {
        return currentActionProperty.get();
    }

    /**
     * Sets the current action type.
     *
     * @param actionType the action type to set.
     */
    public static void setType(ActionType actionType) {
        currentActionProperty.set(actionType);
    }
}
