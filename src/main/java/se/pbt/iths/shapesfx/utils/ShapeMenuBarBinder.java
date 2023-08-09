package se.pbt.iths.shapesfx.utils;

import javafx.collections.ListChangeListener;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import se.pbt.iths.shapesfx.models.ShapeTemplate;
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapeStorage;
import se.pbt.iths.shapesfx.modelsmanagement.SelectedShape;

/**
 * Binds the content of a JavaFX Menu to the list of drawn shapes managed by {@link DrawnShapeStorage}.
 * When a new shape is added to or removed from the DrawnShapesMenu, the menu items are updated accordingly.
 */
public class ShapeMenuBarBinder {
    private final Menu menu;
    private final DrawnShapeStorage drawnShapes;

    public ShapeMenuBarBinder(Menu menu, DrawnShapeStorage drawnShapes) {
        this.menu =  menu;
        this.drawnShapes = drawnShapes;
    }

    /**
     * Initializes the binding of menu items to the shapes in {@link DrawnShapeStorage}.
     * If no shapes exist, displays an "Empty" menu item.
     * Otherwise, displays menu items for each drawn shape, and sets an action to select the shape when its menu item is clicked.
     */
    public void bindMenuItems() {
        drawnShapes.getDrawnShapes().addListener((ListChangeListener<ShapeTemplate>) change -> updateMenuContent());
        updateMenuContent();
    }

    /**
     * Updates the content of the menu based on the shapes stored in {@link DrawnShapeStorage}.
     * If no shapes are present, an "Empty" menu item is displayed.
     * Otherwise, a menu item is created for each shape. Clicking on a shape menu item selects the shape and displays a message.
     */
    private void updateMenuContent() {
        menu.getItems().clear();
        if (drawnShapes.getDrawnShapes().isEmpty()) {
            menu.getItems().add(new MenuItem("Empty"));
        } else {
            for (ShapeTemplate shape : drawnShapes.getDrawnShapes()) {
                menu.getItems().add(createShapeMenuItem(shape));
            }
        }
    }

    /**
     * Creates a new menu item for a given shape template.
     * The menu item, when clicked, will set the shape as the selected shape.
     *
     * @param shape The shape template for which the menu item should be created.
     * @return A MenuItem object corresponding to the given shape template.
     */
    private MenuItem createShapeMenuItem(ShapeTemplate shape) {
        MenuItem menuItem = new MenuItem(shape.getName());
        menuItem.setOnAction(e -> SelectedShape.getInstance().setSelectedShape(shape));
        return menuItem;
    }
}

