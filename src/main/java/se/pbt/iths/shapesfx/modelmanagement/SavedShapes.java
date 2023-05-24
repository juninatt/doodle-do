package se.pbt.iths.shapesfx.modelmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Shape;

public class SavedShapes {

    public static SavedShapes instance;

    ObservableList<Shape> storedShapes;

    public SavedShapes() {
        storedShapes = FXCollections.observableArrayList();
    }

    public static SavedShapes getInstance() {
        if (instance == null) {
            instance = new SavedShapes();
        }
        return instance;
    }

    public void addShape(Shape shape) {
        if (shape != null) {
            storedShapes.add(shape);
        } else {
            throw new IllegalArgumentException("Shape cannot be null");
        }
    }

    public void removeShape(Shape shape) {
        if (shape != null) {
            storedShapes.remove(shape);
        } else {
            throw new IllegalArgumentException("Shape cannot be null");
        }
    }

    public ObservableList<Shape> getSavedShapes() {
        return storedShapes;
    }
}
