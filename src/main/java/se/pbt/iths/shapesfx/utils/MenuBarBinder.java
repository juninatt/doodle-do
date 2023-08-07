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
public class MenuBarBinder {

    private final Menu menu;

    public MenuBarBinder(Menu menu) {
        this.menu = menu;
    }

    /**
     * Initializes the binding of menu items to the shapes in {@link DrawnShapeStorage}.
     * If no shapes exist, displays an "Empty" menu item.
     * Otherwise, displays menu items for each drawn shape, and sets an action to select the shape when its menu item is clicked.
     */
    public void bindMenuItems() {
        updateMenuItems();
        DrawnShapeStorage.getInstance().getDrawnShapes().addListener((ListChangeListener<ShapeTemplate>) change -> updateMenuItems());
    }

    /**
     * Updates the content of the menu based on the shapes stored in {@link DrawnShapeStorage}.
     * If no shapes are present, an "Empty" menu item is displayed.
     * Otherwise, a menu item is created for each shape. Clicking on a shape menu item selects the shape and displays a message.
     */
    private void updateMenuItems() {
        menu.getItems().clear();
        var drawnShapes = DrawnShapeStorage.getInstance().getDrawnShapes();
        if (drawnShapes.isEmpty()) {
            menu.getItems().add(new MenuItem("Empty"));
        } else {
            for (ShapeTemplate shape : drawnShapes) {
                menu.getItems().add(createShapeMenuItem(shape));
            }
        }
    }

    private MenuItem createShapeMenuItem(ShapeTemplate shape) {
        MenuItem menuItem = new MenuItem(shape.getName());
        menuItem.setOnAction(e -> handleShapeMenuItemClick(shape));
        return menuItem;
    }

    private void handleShapeMenuItemClick(ShapeTemplate shape) {
        SelectedShape.getInstance().setSelectedShape(shape);
        InformationTextProvider.getInformationTextProperty().set("Shape '" + shape.getName() + "' selected. Click on the canvas to add it!");
    }
}

