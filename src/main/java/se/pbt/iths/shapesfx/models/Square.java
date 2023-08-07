package se.pbt.iths.shapesfx.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Square extends ShapeTemplate {

    public Square(String name, Paint paint, double sideLength) {
        super(name, paint, sideLength);
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        cx = x - size / 2;
        cy = y - size / 2;
        gc.setFill(getPaint());
        gc.fillRect(cx, cy, size, size);
    }

    @Override
    public boolean contains(double x, double y) {
        var leftX = cx - size / 2;
        var topY = cy - size / 2;
        return x >= leftX && x <= leftX + size && y >= topY && y <= topY + size;
    }
}

