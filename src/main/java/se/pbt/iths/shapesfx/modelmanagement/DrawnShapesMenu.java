package se.pbt.iths.shapesfx.modelmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.pbt.iths.shapesfx.interfaces.ShapeProperties;

// TODO: Make thread safe
// TODO: Return unmodifiable list
// TODO: Forbid duplicate shape names
public class DrawnShapesMenu {

    public static DrawnShapesMenu instance;

    ObservableList<ShapeProperties> storedShapes;

    public DrawnShapesMenu() {
        storedShapes = FXCollections.observableArrayList();
    }

    public static DrawnShapesMenu getInstance() {
        if (instance == null) {
            instance = new DrawnShapesMenu();
        }
        return instance;
    }

    public void addShape(ShapeProperties shape) {
        if (shape != null) {
            storedShapes.add(shape);
        } else {
            throw new IllegalArgumentException("Shape cannot be null");
        }
    }

    public void removeShape(ShapeProperties shape) {
        if (shape != null) {
            storedShapes.remove(shape);
        } else {
            throw new IllegalArgumentException("Shape cannot be null");
        }
    }

    public ObservableList<ShapeProperties> getSavedShapes() {
        return storedShapes;
    }
}
