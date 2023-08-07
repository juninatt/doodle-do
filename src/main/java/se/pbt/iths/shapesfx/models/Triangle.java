package se.pbt.iths.shapesfx.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Triangle extends ShapeTemplate {

    public Triangle(String name, Paint paint, double size) {
        super(name, paint, size);
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        // Calculate half of the triangle's height.
        double halfHeight = (Math.sqrt(3) / 2) * size / 2.0;

        // Calculate half of the triangle's side length.
        double halfSide = size / 2.0;

        // Define the x-coordinates of the triangle's vertices.
        double[] xPoints = {x - halfSide, x + halfSide, x};

        // Define the y-coordinates of the triangle's vertices.
        double[] yPoints = {y + halfHeight, y + halfHeight, y - halfHeight};

        gc.setFill(getPaint());
        gc.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public boolean contains(double x, double y) {
        // Calculate the triangle's height.
        double height = (Math.sqrt(3) / 2) * size;

        // Translate the point to the coordinate system where the triangle is centered at the origin.
        x -= getCx();
        y -= getCy();

        // Check if the point lies within the bounds of the triangle.
        return (x >= -size / 2) && (x <= size / 2) && (y >= -height / 2) && (y <= height / 2);
    }
}
