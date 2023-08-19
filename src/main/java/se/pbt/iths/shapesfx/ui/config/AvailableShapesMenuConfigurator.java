package se.pbt.iths.shapesfx.ui.config;

import javafx.collections.ListChangeListener;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import se.pbt.iths.shapesfx.models.shapes.ShapeTemplate;
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapeStorage;
import se.pbt.iths.shapesfx.modelsmanagement.SelectedShape;

/**
 * Binds the content of a JavaFX Menu to the list of drawn shapes managed by {@link DrawnShapeStorage}.
 * When a new shape is added to or removed from the drawn shapes storage, the menu items are updated accordingly.
 */
public class AvailableShapesMenuConfigurator {

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
    private final DrawnShapeStorage drawnShapes;

    /**
     * Constructs a new AvailableShapesMenuConfigurator with the provided menu and drawn shapes storage.
     *
     * @param menu The JavaFX menu to be configured.
     * @param drawnShapes The storage containing the list of drawn shapes.
     */
    public AvailableShapesMenuConfigurator(Menu menu, DrawnShapeStorage drawnShapes) {
        this.menu =  menu;
        this.drawnShapes = drawnShapes;
    }

    /**
     * Sets up the menu to reflect the current shapes in {@link DrawnShapeStorage}.
     * An "Empty" menu item is shown if no shapes are present. Otherwise, creates a menu item for each shape.
     */
    private void updateMenuContent() {
        menu.getItems().clear();
        if (drawnShapes.getDrawnShapes().isEmpty()) {
            menu.getItems().add(new MenuItem(EMPTY_MENU));
        } else {
            for (ShapeTemplate shape : drawnShapes.getDrawnShapes()) {
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
        drawnShapes.getDrawnShapes().addListener((ListChangeListener<ShapeTemplate>) change -> updateMenuContent());
        updateMenuContent();
    }
}

