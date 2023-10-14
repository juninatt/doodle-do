package se.pbt.iths.doodledo.modelsmanagement;

import javafx.collections.ObservableList;
import se.pbt.iths.doodledo.models.shapes.ShapeTemplate;

import java.util.Optional;

/**
 * The DrawnShapeStorage class manages the shapes created by the user.
 * The shapes are stored in an ObservableList to notify any observers of changes.
 */
public class DrawnShapeStorage {
    private final ObservableList<ShapeTemplate> drawnShapes;


    public DrawnShapeStorage(ObservableList<ShapeTemplate> drawnShapes) {
        this.drawnShapes = drawnShapes;
    }


    /**
     * Adds a new shape to the ObservableList of stored shapes, if the shape's name is not already in use.
     *
     * @param newShape The shape to be added.
     * @throws IllegalArgumentException if the shape is null or if the shape's name is already in use.
     */
    public void addShape(ShapeTemplate newShape) {
        if (newShape == null) {
            throw new IllegalArgumentException("Shape cannot be null");
        }

        boolean nameExists = drawnShapes.stream()
                .anyMatch(storedShape -> storedShape.getName().equals(newShape.getName()));

        if (nameExists) {
            throw new IllegalArgumentException("Duplicate shape name is not allowed");
        }
        drawnShapes.add(newShape);
    }

    /**
     * Removes a shape from the ObservableList of stored shapes.
     *
     * @param shape The shape to be removed.
     * @throws IllegalArgumentException if the shape is null.
     */
    public void removeShape(ShapeTemplate shape) {
        if (shape != null) {
            drawnShapes.remove(shape);
        } else {
            throw new IllegalArgumentException("Shape cannot be null");
        }
    }

    /**
     * Returns the ObservableList of stored shapes, to allow observers to react to changes.
     *
     * @return the ObservableList of stored shapes.
     */
    public synchronized ObservableList<ShapeTemplate> getDrawnShapes() {
        return drawnShapes;
    }

    /**
     * Retrieves a shape from the drawn shapes based on its name.
     *
     * @param name the name of the shape to retrieve.
     * @return an Optional containing the found shape if present, or an empty Optional if no shape with the given name is found.
     */
    public Optional<ShapeTemplate> get(String name) {
        return drawnShapes.stream()
                .filter(shape -> shape.getName().equals(name))
                .findFirst();
    }
}
