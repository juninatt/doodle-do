package se.pbt.iths.shapesfx.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents an equilateral triangle shape. This class extends the {@link ShapeTemplate}
 * and provides specific implementations for drawing, SVG path conversion, and point containment
 * related to equilateral triangles.
 *
 * @see ShapeTemplate
 */
public class Triangle extends ShapeTemplate {

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

    private double getHeight() {
        return (Math.sqrt(3) / 2) * size;
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
            // Triangle dimensions
            double height = (Math.sqrt(3) / 2) * size;

            // Calculate the triangle's vertices based on the input (x,y) coordinates.
            double[] xPoints = {
                    x - size/2,         // Bottom left
                    x + size/2,         // Bottom right
                    x                   // Top
            };

            double[] yPoints = {
                    y + height/3,       // Bottom left
                    y + height/3,       // Bottom right
                    y - 2*height/3      // Top
            };

            gc.setFill(getPaint());
            gc.fillPolygon(xPoints, yPoints, 3);
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
        // Calculate the triangle's height.
        double height = getHeight();

        // Translate the point to the coordinate system where the triangle is centered at the origin.
        x -= getCx();
        y -= getCy();

        // Check if the point lies within the bounds of the triangle.
        return (x >= -size / 2) && (x <= size / 2) && (y >= -height / 2) && (y <= height / 2);
    }
}
