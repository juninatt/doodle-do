package se.pbt.iths.shapesfx.modelsmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.pbt.iths.shapesfx.models.ShapeTemplate;

/**
 * The DrawnShapesMenu class manages the shapes created by the user.
 * It follows the Singleton design pattern to ensure only one instance of this class exists.
 * This single instance can be accessed globally, which provides a way to access the stored shapes from any other class.
 * The shapes are stored in an ObservableList to notify any observers of changes.
 */
public class DrawnShapeStorage {
    private final ObservableList<ShapeTemplate> storedShapes;


    public DrawnShapeStorage() {
        storedShapes = FXCollections.observableArrayList();
    }


    /**
     * This method provides a global access point for the Singleton instance of DrawnShapesMenu.
     * On first call, it initializes the InstanceHolder class, which in turn initializes the Singleton instance.
     *
     * @return the Singleton instance of DrawnShapesMenu
     */
    public static DrawnShapeStorage getInstance() {
        return InstanceHolder.instance;
    }


    /**
     * Adds a new shape to the ObservableList of stored shapes, if the shape's name is not already in use.
     *
     * @param newShape The shape to be added.
     * @throws IllegalArgumentException if the shape is null or if the shape's name is already in use.
     */
    public synchronized void addShape(ShapeTemplate newShape) {
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

    /**
     * Removes a shape from the ObservableList of stored shapes.
     *
     * @param shape The shape to be removed.
     * @throws IllegalArgumentException if the shape is null.
     */
    public synchronized void removeShape(ShapeTemplate shape) {
        if (shape != null) {
            storedShapes.remove(shape);
        } else {
            throw new IllegalArgumentException("Shape cannot be null");
        }
    }

    /**
     * Returns the ObservableList of stored shapes, to allow observers to react to changes.
     *
     * @return the ObservableList of stored shapes
     */
    public synchronized ObservableList<ShapeTemplate> getSavedShapes() {
        return storedShapes;
    }

    /**
     * The InstanceHolder is a private static class that contains the Singleton instance of DrawnShapesMenu.
     * It is initialized when getInstance() is first called, ensuring lazy initialization and thread-safety.
     */
    private static final class InstanceHolder {
        public static final DrawnShapeStorage instance = new DrawnShapeStorage();
    }
}
