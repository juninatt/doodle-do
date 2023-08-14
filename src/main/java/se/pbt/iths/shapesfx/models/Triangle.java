package se.pbt.iths.shapesfx.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import se.pbt.iths.shapesfx.interfaces.Rotatable;
import se.pbt.iths.shapesfx.utils.ShapeRotator;

/**
 * Represents an equilateral triangle shape. This class extends the {@link ShapeTemplate}
 * and provides specific implementations for drawing, SVG path conversion, and point containment
 * related to equilateral triangles.
 *
 * @see ShapeTemplate
 */
public class Triangle extends ShapeTemplate implements Rotatable {
    private static final int NUM_VERTICES = 3;

    /**
     * Constructs an equilateral triangle with the given name, paint, and size.
     *
     * @param name  The name of the triangle.
     * @param paint The paint used to fill the triangle when drawn.
     * @param size  The side length of the equilateral triangle.
     */
    public Triangle(String name, Paint paint, double size) {
        super(name, paint, size);
    }


    /**
     * Provides an SVG path representation of the triangle.
     *
     * @return A string representing the triangle as an SVG path.
     */
    @Override
    public String toSvgPath() {
        double halfHeight = getHeight() / 2.0;
        double halfSide = size / 2.0;
        double x1 = cx - halfSide;
        double y1 = cy + halfHeight;
        double x2 = cx + halfSide;
        return String.format("M %f %f L %f %f L %f %f Z", cx, cy - halfHeight, x1, y1, x2, y1);
    }

    /**
     * Draws an equilateral triangle on the provided GraphicsContext, using the specified x and y as the centroid of the triangle.
     *
     * @param gc The GraphicsContext on which the triangle is drawn.
     * @param x  The x-coordinate of the centroid of the triangle.
     * @param y  The y-coordinate of the centroid of the triangle.
     */
    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        cx = x;
        cy = y;
        double height = (Math.sqrt(3) / 2) * size;

        if (vertices == null)
            setVertices(x, y, height);

        gc.setFill(getPaint());
        gc.fillPolygon(vertices[ROW_X], vertices[ROW_Y], NUM_VERTICES);
    }

    /**
     * Sets the vertices for a shape based on the given x, y coordinates and height.
     * The method constructs the vertices for a triangle with the specified parameters.
     *
     * @param x       The x-coordinate of the center of the shape.
     * @param y       The y-coordinate of the center of the shape.
     * @param height  The height of the shape.
     */
    private void setVertices(double x, double y, double height) {
        vertices = new double[2][NUM_VERTICES];

        vertices[ROW_X] = new double[]{
                x - size / 2,         // Bottom left
                x + size / 2,         // Bottom right
                x                     // Top
        };

        vertices[ROW_Y] = new double[]{
                y + height / 3,       // Bottom left
                y + height / 3,       // Bottom right
                y - 2 * height / 3    // Top
        };
    }

    /**
     * Determines if the specified point (x, y) lies within the boundaries of the triangle.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @return {@code true} if the point is within the triangle; {@code false} otherwise.
     */
    @Override
    public boolean contains(double x, double y) {
        double height = getHeight();

        // Translate the point to the coordinate system where the triangle is centered at the origin.
        x -= getCx();
        y -= getCy();

        // Check if the point lies within the bounds of the triangle.
        return (x >= -size / 2) && (x <= size / 2) && (y >= -height / 2) && (y <= height / 2);
    }

    @Override
    public void rotate(ShapeRotator rotator, double angle) {
        vertices = rotator.rotatePoints(vertices, cx, cy, angle);
    }

    private double getHeight() {
        return (Math.sqrt(3) / 2) * size;
    }
}
