package se.pbt.iths.shapesfx.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import se.pbt.iths.shapesfx.interfaces.Drawable;

public class MySquare extends Rectangle implements Drawable {

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

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        double sideLength = getWidth();
        double centerX = x - sideLength / 2;
        double centerY = y - sideLength / 2;
        gc.setFill(getPaint());
        gc.fillRect(centerX, centerY, getWidth(), getHeight());
    }
}

