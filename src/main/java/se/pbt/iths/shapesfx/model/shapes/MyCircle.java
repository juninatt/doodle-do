package se.pbt.iths.shapesfx.model.shapes;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class MyCircle extends Circle {
    private Paint paint;
    private boolean selected;

    public MyCircle(double radius, Paint paint) {
        super(radius);
        this.paint = paint;
    }

    public Paint getPaint() {
        return paint;
    }
    public void setColor(Paint paint) {
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

