package se.pbt.iths.shapesfx.utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

import java.util.function.Consumer;

public class MenuActionUtils {

    public static EventHandler<ActionEvent> createShapeMenuAction(Consumer<String> onMenuItemClick) {
        return event -> {
            MenuItem menuItem = (MenuItem) event.getSource();
            onMenuItemClick.accept(menuItem.getText());
        };
    }
}
