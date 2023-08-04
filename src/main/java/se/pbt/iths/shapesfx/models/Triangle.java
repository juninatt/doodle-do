package se.pbt.iths.shapesfx.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Triangle extends ShapeTemplate {

    private double sideLength;

    public Triangle(String name, Paint paint, double sideLength) {
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
        // Calculate half of the triangle's height.
        double halfHeight = (Math.sqrt(3) / 2) * sideLength / 2.0;

        // Calculate half of the triangle's side length.
        double halfSide = sideLength / 2.0;

        // Define the x-coordinates of the triangle's vertices.
        double[] xPoints = {x - halfSide, x + halfSide, x};

        // Define the y-coordinates of the triangle's vertices.
        double[] yPoints = {y + halfHeight, y + halfHeight, y - halfHeight};

        gc.setFill(getPaint());
        gc.fillPolygon(xPoints, yPoints, 3);
    }
}
