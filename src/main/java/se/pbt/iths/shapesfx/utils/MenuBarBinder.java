package se.pbt.iths.shapesfx.utils;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import se.pbt.iths.shapesfx.interfaces.Drawable;
import se.pbt.iths.shapesfx.interfaces.ShapeProperties;
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapesMenu;
import se.pbt.iths.shapesfx.modelsmanagement.SelectedShape;

public class MenuBarBinder {

    private final Menu menu;

    public MenuBarBinder(Menu menu) {
        this.menu = menu;
    }

    public void bindMenuItems() {
        updateMenuItems();
        DrawnShapesMenu.getInstance().getSavedShapes().addListener((ListChangeListener<ShapeProperties>) change -> {
            updateMenuItems();
        });
    }

    private void updateMenuItems() {
        menu.getItems().clear();
        ObservableList<Drawable> shapeProperties = DrawnShapesMenu.getInstance().getSavedShapes();
        System.out.println("Updating in binder class: " + shapeProperties);
        if (shapeProperties.isEmpty()) {
            menu.getItems().add(new MenuItem("Empty"));
        } else {
            for (Drawable shape : shapeProperties) {
                MenuItem menuItem = new MenuItem(shape.getName());
                menuItem.setOnAction(e -> {
                    SelectedShape.getInstance().setSelectedShape( shape);
                    InformationTextProvider.getInformationTextProperty().set("Shape '" + shape.getName() + "' selected. Click on the canvas to add it!");
                });
                menu.getItems().add(menuItem);
            }
        }
    }
}

