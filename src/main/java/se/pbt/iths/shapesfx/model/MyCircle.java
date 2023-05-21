package se.pbt.iths.shapesfx.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MyCircle extends Circle {
    private Color color;
    private boolean selected;

    public MyCircle(double centerX, double centerY, double radius, Color color) {
        super(centerX, centerY, radius);
        this.color = color;
        this.selected = false;
        setFill(color);
    }

    // Additional methods specific to MyCircle class

    public void setColor(Color color) {
        this.color = color;
        setFill(color);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        // Update appearance or perform any other necessary actions
    }
}

