package se.pbt.iths.shapesfx.model;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MySquare extends Rectangle {
    private Paint paint;
    private boolean selected;

    public MySquare(double size, Paint paint) {
        super(size, size);
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
}

