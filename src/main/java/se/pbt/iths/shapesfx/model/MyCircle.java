package se.pbt.iths.shapesfx.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MyCircle extends Circle {
    private Color color;
    private boolean selected;

    public MyCircle(double radius, Color color) {
        super(radius);
        this.color = color;
    }

    public MyCircle(double centerX, double centerY, double radius, Color color) {
        super(centerX, centerY, radius);
        this.color = color;
        this.selected = false;
        setFill(color);
    }

    public void setColor(Color color) {
        this.color = color;
        setFill(color);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}

