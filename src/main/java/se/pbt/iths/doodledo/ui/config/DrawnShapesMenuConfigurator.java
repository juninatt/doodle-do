package se.pbt.iths.doodledo.ui.config;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import se.pbt.iths.doodledo.models.shapes.ShapeTemplate;
import se.pbt.iths.doodledo.modelsmanagement.DrawnShapeStorage;
import se.pbt.iths.doodledo.modelsmanagement.SelectedShape;

/**
 * Binds the content of a JavaFX Menu to the list of drawn shapes managed by {@link DrawnShapeStorage}.
 * When a new shape is added to or removed from the drawn shapes storage, the menu items are updated accordingly.
 */
public class DrawnShapesMenuConfigurator {

    /**
     * Default menu item text displayed when no shapes are present.
     */
    private final static String EMPTY_MENU = "Empty";

    /**
     * The JavaFX menu that will display available shapes.
     */
    private final Menu menu;

    /**
     * The storage managing the collection of drawn shapes.
     */
    private final ObservableList<ShapeTemplate> drawnShapesList;

    /**
     * Constructs a new object with the provided menu and drawn shapes storage.
     *
     * @param menu The JavaFX menu to be configured.
     * @param drawnShapesList The list of drawn shapes.
     */
    public DrawnShapesMenuConfigurator(Menu menu, ObservableList<ShapeTemplate> drawnShapesList) {
        this.menu =  menu;
        this.drawnShapesList = drawnShapesList;
    }

    /**
     * Sets up the menu to reflect the current shapes in {@link DrawnShapeStorage}.
     * An "Empty" menu item is shown if no shapes are present. Otherwise, creates a menu item for each shape.
     */
    private void updateMenuContent() {
        menu.getItems().clear();
        if (drawnShapesList.isEmpty()) {
            menu.getItems().add(new MenuItem(EMPTY_MENU));
        } else {
            for (ShapeTemplate shape : drawnShapesList) {
                menu.getItems().add(createShapeMenuItem(shape));
            }
        }
    }

    /**
     * Constructs a menu item for the given shape.
     * Selects the shape and shows a message when the menu item is activated.
     *
     * @param shape The shape to represent as a menu item.
     * @return A new MenuItem corresponding to the shape.
     */
    private MenuItem createShapeMenuItem(ShapeTemplate shape) {
        var menuItem = new MenuItem(shape.getName());
        menuItem.setOnAction(e -> SelectedShape.getInstance().setSelectedShape(shape));
        return menuItem;
    }

    /**
     * Configures the menu to update as the list of drawn shapes changes.
     * Also performs an initial menu content update.
     */
    public void configure() {
        drawnShapesList.addListener((ListChangeListener<ShapeTemplate>) change -> updateMenuContent());
        updateMenuContent();
    }
}

