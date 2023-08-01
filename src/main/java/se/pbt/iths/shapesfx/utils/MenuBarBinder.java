package se.pbt.iths.shapesfx.utils;

import javafx.collections.ListChangeListener;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.shape.Shape;
import se.pbt.iths.shapesfx.interfaces.ShapeProperties;
import se.pbt.iths.shapesfx.modelmanagement.DrawnShapesMenu;
import se.pbt.iths.shapesfx.modelmanagement.SelectedShape;

public class MenuBarBinder {

    private final Menu menu;

    public MenuBarBinder(Menu menu) {
        this.menu = menu;
    }

    public void bindMenuItems() {
        DrawnShapesMenu.getInstance().getSavedShapes().addListener((ListChangeListener<ShapeProperties>) change -> {
            menu.getItems().clear();

            if (change.getList().isEmpty()) {
                menu.getItems().add(new MenuItem("Empty"));
            } else {
                for (ShapeProperties shape : change.getList()) {
                    MenuItem menuItem = new MenuItem(shape.getName());
                    menuItem.setOnAction(e -> {
                        SelectedShape.getInstance().setSelectedShape((Shape) shape);
                        InformationTextProvider.getInformationTextProperty().set("Shape '" + shape.getName() + "' selected. Click on the canvas to add it!");
                    });
                    menu.getItems().add(menuItem);
                }
            }
        });
    }
}

