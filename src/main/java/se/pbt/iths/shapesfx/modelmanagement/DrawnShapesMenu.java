package se.pbt.iths.shapesfx.modelmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.pbt.iths.shapesfx.interfaces.ShapeProperties;

public class DrawnShapesMenu {

    ObservableList<ShapeProperties> storedShapes;

    public DrawnShapesMenu() {
        storedShapes = FXCollections.observableArrayList();
    }

    private static final class InstanceHolder {
        public static final DrawnShapesMenu instance = new DrawnShapesMenu();
    }

    public static DrawnShapesMenu getInstance() {
        return InstanceHolder.instance;
    }


    public synchronized void addShape(ShapeProperties newShape) {
        boolean nameExists = storedShapes.stream()
                .anyMatch(storedShape -> storedShape.getName().equals(newShape.getName()));
        if (newShape != null) {
            if (nameExists) {
                throw new IllegalArgumentException("Duplicate shape name is not allowed");
            }
            storedShapes.add(newShape);
        } else {
            throw new IllegalArgumentException("Shape cannot be null");
        }
    }

    public synchronized void removeShape(ShapeProperties shape) {
        if (shape != null) {
            storedShapes.remove(shape);
        } else {
            throw new IllegalArgumentException("Shape cannot be null");
        }
    }

    public synchronized ObservableList<ShapeProperties> getSavedShapes() {
        return FXCollections.unmodifiableObservableList(storedShapes);
    }
}
