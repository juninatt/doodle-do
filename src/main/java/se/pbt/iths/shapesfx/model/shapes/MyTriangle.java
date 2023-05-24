package se.pbt.iths.shapesfx.model.shapes;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class MyTriangle extends Polygon {

    private String name;
    private Paint paint;
    double size;
    private boolean selected;

    public MyTriangle(String name, double size, Paint paint) {
        super();
        this.name = name;
        this.size = size;
        this.paint = paint;
        this.selected = false;
        setFill(paint);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
        setFill(paint);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
