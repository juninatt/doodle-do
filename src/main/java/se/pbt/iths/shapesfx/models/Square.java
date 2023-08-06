package se.pbt.iths.shapesfx.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Square extends ShapeTemplate {

    private double sideLength;

    public Square(String name, Paint paint, double sideLength) {
        super(name, paint);
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        cx = x - sideLength / 2;
        cy = y - sideLength / 2;
        gc.setFill(getPaint());
        gc.fillRect(cx, cy, sideLength, sideLength);
    }

    @Override
    public boolean contains(double x, double y) {
        var leftX = cx - sideLength / 2;
        var topY = cy + sideLength / 2;
        return x >= leftX && x <= leftX + sideLength && y >= topY && y <= topY + sideLength;
    }
}

