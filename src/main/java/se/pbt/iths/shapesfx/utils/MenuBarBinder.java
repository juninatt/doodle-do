package se.pbt.iths.shapesfx.utils;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import se.pbt.iths.shapesfx.models.ShapeTemplate;
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapesMenu;
import se.pbt.iths.shapesfx.modelsmanagement.SelectedShape;

public class MenuBarBinder {

    private final Menu menu;

    public MenuBarBinder(Menu menu) {
        this.menu = menu;
    }

    public void bindMenuItems() {
        updateMenuItems();
        DrawnShapesMenu.getInstance().getSavedShapes().addListener((ListChangeListener<ShapeTemplate>) change -> updateMenuItems());
    }

    private void updateMenuItems() {
        menu.getItems().clear();
        ObservableList<ShapeTemplate> shapeProperties = DrawnShapesMenu.getInstance().getSavedShapes();
        if (shapeProperties.isEmpty()) {
            menu.getItems().add(new MenuItem("Empty"));
        } else {
            for (ShapeTemplate shape : shapeProperties) {
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
