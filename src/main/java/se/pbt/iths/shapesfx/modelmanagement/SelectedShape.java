package se.pbt.iths.shapesfx.modelmanagement;

import javafx.scene.shape.Shape;

public class SelectedShape {
    private static SelectedShape instance;
    private Shape selectedShape;

    private SelectedShape() {
        // Private constructor to prevent direct instantiation
    }

    public static SelectedShape getInstance() {
        if (instance == null) {
            instance = new SelectedShape();
        }
        return instance;
    }

    public void setSelectedShape(Shape shape) {
        selectedShape = shape;
    }

    public Shape getSelectedShape() {
        return selectedShape;
    }

    public void reset() {
        selectedShape = null;
    }
}
