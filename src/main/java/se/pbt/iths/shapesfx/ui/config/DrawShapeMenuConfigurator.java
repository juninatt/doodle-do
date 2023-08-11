package se.pbt.iths.shapesfx.ui.config;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.enums.ActionType;
import se.pbt.iths.shapesfx.ui.utils.ActionTypeProvider;

/**
 * Configures the drawing menu, associating menu items with specific actions related to shape drawing.
 * It also provides integration with an FXML stage configurator to load specific views based on menu actions.
 */
public class DrawShapeMenuConfigurator {

    private final Menu menu;

    /**
     * Constructs a new DrawShapeMenuConfigurator using the given menu and an instance of {@link FXMLStageConfigurator}.
     *
     * @param menu         the menu to be configured.
     */
    public DrawShapeMenuConfigurator(Menu menu) {
        this.menu = menu;
    }

    /**
     * Configures the menu items of the menu by setting their action events.
     * Each menu item, when activated, sets the action type to DRAW and loads the "create-shape-view.fxml" view..
     */
    public void configure() {
        menu.getItems().forEach(menuItem -> menuItem.setOnAction(event -> {
            ActionTypeProvider.setType(ActionType.DRAW);
            MenuItem sourceItem = (MenuItem) event.getSource();
            var configurator = new FXMLStageConfigurator(new Stage());
            configurator.getConfiguredStage(sourceItem.getText(), "create-shape-view.fxml")
                    .showAndWait();
        }));
    }
}
