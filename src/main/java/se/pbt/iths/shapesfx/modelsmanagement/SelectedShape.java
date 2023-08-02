package se.pbt.iths.shapesfx.modelsmanagement;

import se.pbt.iths.shapesfx.interfaces.Drawable;

public class SelectedShape {
    private static SelectedShape instance;
    private Drawable selectedShape;

    private SelectedShape() {
        // Private constructor to prevent direct instantiation
    }

    public static SelectedShape getInstance() {
        if (instance == null) {
            instance = new SelectedShape();
        }
        return instance;
    }

    public void setSelectedShape(Drawable shape) {
        selectedShape = shape;
    }

    public Drawable getSelectedShape() {
        return selectedShape;
    }

    public void reset() {
        selectedShape = null;
    }
}
