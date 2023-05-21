package se.pbt.iths.shapesfx.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MySquare extends Rectangle {
    private Color color;
    private boolean selected;

    public MySquare(double x, double y, double size, Color color) {
        super(x, y, size, size);
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

