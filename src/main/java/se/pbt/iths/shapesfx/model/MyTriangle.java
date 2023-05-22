package se.pbt.iths.shapesfx.model;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class MyTriangle extends Polygon {
    private Paint paint;
    double sideLength;
    private boolean selected;

    public MyTriangle(double sideLength, Paint paint) {
        super();
        this.sideLength = sideLength;
        this.paint = paint;
    }


    public void setPaint(Paint paint) {
        this.paint = paint;
        setFill(paint);
    }

    public Paint getPaint() {
        return paint;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public double getSideLength() {
        return sideLength;
    }
}
