package se.pbt.iths.shapesfx.shape;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class MyTriangle extends Polygon {
    private Color color;
    private boolean selected;

    public MyTriangle(double x1, double y1, double x2, double y2, double x3, double y3, Color color) {
        super(x1, y1, x2, y2, x3, y3);
        this.color = color;
        this.selected = false;
        setFill(color);
    }

    // Additional methods specific to MyTriangle class

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
