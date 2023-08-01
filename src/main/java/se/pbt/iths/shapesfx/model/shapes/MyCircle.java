package se.pbt.iths.shapesfx.model.shapes;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import se.pbt.iths.shapesfx.interfaces.ShapeProperties;

public class MyCircle extends Circle implements ShapeProperties {

    private String name;
    private Paint paint;
    private boolean selected;

    public MyCircle(String name, double radius, Paint paint) {
        super(radius);
        this.name = name;
        this.paint = paint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

