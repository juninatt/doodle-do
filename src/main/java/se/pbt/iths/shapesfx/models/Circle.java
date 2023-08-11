package se.pbt.iths.shapesfx.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents a circle shape. This class extends the {@link ShapeTemplate}
 * and provides specific implementations for drawing, SVG path conversion, and point containment
 * related to circles.
 *
 * @see ShapeTemplate
 */
public class Circle extends ShapeTemplate {

    /**
     * Constructor to initialize the circle with the essential properties.
     *
     * @param name      The name of the circle.
     * @param fillColor The fill color of the circle.
     * @param diameter  The diameter of the circle.
     */
    public Circle(String name, Paint fillColor, double diameter) {
        super(name, fillColor, diameter);
    }

    /**
     * Converts the circle's properties to an SVG path representation.
     *
     * @return SVG path string representing the circle.
     */
    @Override
    public String toSvgPath() {
        var radius = getSize() / 2;
        return String.format("M %f %f m -%f, 0 a %f,%f 0 1,0 %f,0 a %f,%f 0 1,0 -%f,0", cx, cy, radius, radius, radius, radius * 2, radius, radius, radius * 2);
    }

    /**
     * Draws the circle on a graphics context.
     *
     * @param gc The graphics context where the circle is to be drawn.
     * @param x  The X-coordinate of the circle's center.
     * @param y  The Y-coordinate of the circle's center.
     */
    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        cx = x;
        cy = y;
        var radius = getSize() / 2;
        gc.setFill(getPaint());
        gc.fillOval(x - radius / 2, y - radius / 2, radius, radius);
    }

    /**
     * Determines if a point (x,y) is within the circle's boundaries.
     *
     * @param x The X-coordinate of the point.
     * @param y The Y-coordinate of the point.
     * @return True if the point is inside the circle, otherwise false.
     */
    @Override
    public boolean contains(double x, double y) {
        double dx = cx - x;
        double dy = cy - y;
        var radius = getSize() / 2;
        double distanceSquared = dx * dx + dy * dy;
        return distanceSquared <= radius * radius;
    }
}

