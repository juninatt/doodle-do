package se.pbt.iths.shapesfx.modelsmanagement;

import se.pbt.iths.shapesfx.models.ShapeTemplate;

public class SelectedShape {
    private static SelectedShape instance;
    private ShapeTemplate selectedShape;

    private SelectedShape() {
        // Private constructor to prevent direct instantiation
    }

    public static SelectedShape getInstance() {
        if (instance == null) {
            instance = new SelectedShape();
        }
        return instance;
    }

    public void setSelectedShape(ShapeTemplate shape) {
        selectedShape = shape;
    }

    public ShapeTemplate getSelectedShape() {
        return selectedShape;
    }

    public void reset() {
        selectedShape = null;
    }

    public String getName() {
        return selectedShape.getName();
    }
}
