package se.pbt.iths.shapesfx.modelsmanagement;

import se.pbt.iths.shapesfx.models.shapes.ShapeTemplate;

/**
 * Singleton class to represent and manage the currently selected shape.
 */
public class SelectedShape {
    private static SelectedShape instance;
    private ShapeTemplate selectedShape;

    /**
     * Private constructor to prevent direct instantiation.
     */
    private SelectedShape() {}

    /**
     * Gets the singleton instance of SelectedShape, creating it if necessary.
     * @return The singleton instance of SelectedShape
     */
    public static SelectedShape getInstance() {
        if (instance == null) {
            instance = new SelectedShape();
        }
        return instance;
    }

    /**
     * Set the selected shape to null
     */
    public void reset() {
        selectedShape = null;
    }

    public void setSelectedShape(ShapeTemplate shape) {
        selectedShape = shape;
    }

    public ShapeTemplate getSelectedShape() {
        return selectedShape;
    }

    public String getName() {
        return selectedShape.getName();
    }
}
