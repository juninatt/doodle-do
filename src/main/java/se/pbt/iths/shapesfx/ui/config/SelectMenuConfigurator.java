package se.pbt.iths.shapesfx.ui.config;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import se.pbt.iths.shapesfx.enums.ActionType;
import se.pbt.iths.shapesfx.ui.utils.ActionTypeProvider;

/**
 * Configures the select menu items and associates each item with a specific action.
 * Menu item texts are expected to match the ActionType enum values for setting the correct action type.
 */
public class SelectMenuConfigurator {

    private final Menu menu;

    /**
     * Constructs a new SelectMenuConfigurator with the provided menu.
     *
     * @param menu the menu to be configured.
     */
    public SelectMenuConfigurator(Menu menu) {
        this.menu = menu;
    }

    /**
     * Iterates over the menu items of the select menu and sets their action events.
     */
    public void configure() {
        menu.getItems().forEach(this::configureMenuItem);
    }

    /**
     * Associates the provided menu item with its action event.
     *
     * @param menuItem the menu item to be configured.
     */
    private void configureMenuItem(MenuItem menuItem) {
        menuItem.setOnAction(this::setAction);
    }

    /**
     * Determines the action associated with a given menu item based on its text.
     * It attempts to map the item text to an ActionType enum value.
     *
     * @param event the action event triggered by the menu item.
     */
    private void setAction(ActionEvent event) {
        var menuItem = (MenuItem) event.getSource();
        var text = menuItem.getText().toUpperCase();
        try {
            ActionTypeProvider.setType(ActionType.valueOf(text));
        } catch (IllegalArgumentException illegalArgumentException) {
            handleInvalidAction(illegalArgumentException);
        }
    }

    /**
     * Handles an invalid action by defaulting to the EMPTY action and printing the exception.
     *
     * @param illegalArgumentException the exception that occurred.
     */
    private void handleInvalidAction(IllegalArgumentException illegalArgumentException) {
        ActionTypeProvider.setType(ActionType.EMPTY);
        illegalArgumentException.printStackTrace();
    }
}

