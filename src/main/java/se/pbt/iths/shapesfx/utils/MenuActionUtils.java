package se.pbt.iths.shapesfx.utils;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.util.function.Consumer;

/**
 * Utility class for abstracting menu-related operations in JavaFX.
 */
public class MenuActionUtils {


    /**
     * Binds the provided action to each item in the provided menu.
     *
     * @param menu The menu whose items should receive the action.
     * @param onMenuItemClick A Consumer accepting the text of the clicked menu item.
     */
    public static void bindActionToMenuItems(Menu menu, Consumer<String> onMenuItemClick) {
        menu.getItems().forEach(menuItem -> menuItem.setOnAction(event -> {
            MenuItem sourceItem = (MenuItem) event.getSource();
            onMenuItemClick.accept(sourceItem.getText());
        }));
    }
}
