package se.pbt.iths.shapesfx.model;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class MyTriangle extends Polygon {
    private Paint paint;
    double size;
    private boolean selected;

    public MyTriangle(double size, Paint paint) {
        super();
        this.size = size;
        this.paint = paint;
        this.selected = false;
        setFill(paint);
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

    public double getSize() {
        return size;
    }
}
