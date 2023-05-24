package se.pbt.iths.shapesfx.model.shapes;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MySquare extends Rectangle {

    private String name;
    private Paint paint;
    private boolean selected;

    public MySquare(String name, double size, Paint paint) {
        super(size, size);
        this.name = name;
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

