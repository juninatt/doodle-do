package se.pbt.iths.doodledo.models.shapes.nonpolygonal;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import se.pbt.iths.doodledo.models.shapes.ShapeTemplate;

import java.util.Locale;

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

    public Circle(String name, Paint fillColor, double diameter, Point2D center) {
        super(name, fillColor, diameter);
        this.center = center;
    }

    /**
     * Converts the circle's properties to an SVG path representation.
     *
     * @return SVG path string representing the circle.
     */
    @Override
    public String toSvgPath() {
        var radius = getSize() / 2;
        return String.format(Locale.ENGLISH, "M %.6f %.6f m -%.6f, 0 a %.6f,%.6f 0 1,0 %.6f,0 a %.6f,%.6f 0 1,0 -%.6f,0", center.getX(), center.getY(), radius, radius, radius, radius * 2, radius, radius, radius * 2);
    }

    /**
     * Draws the circle on a graphics context.
     *
     * @param gc The graphics context where the circle is to be drawn.
     * @param point The center of the shape
     */
    @Override
    public void draw(GraphicsContext gc, Point2D point) {
        // Set the center coordinates of the circle
        point = point;

        var diameter = size;

        // Draw the filled oval (circle) using the specified coordinates, radius, and fill color
        gc.setFill(getPaint());
        gc.fillOval(point.getX() - diameter / 2, point.getY() - diameter / 2, diameter, diameter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Point2D coordinates) {
        // Find the difference in x and y coordinates from the point to the center of the circle
        double horizontalDifference = coordinates.getX() - center.getX();
        double verticalDifference = coordinates.getY() - center.getY();

        // Determine the square of the distance from the point to the center of the circle
        // (Uses the Pythagorean theorem, avoiding the square root for efficiency)
        double distanceSquared = horizontalDifference * horizontalDifference + verticalDifference * verticalDifference;

        // Calculate the squared radius of the circle
        double squaredRadius = Math.pow(getSize() / 2, 2);

        // Check if the point's distance from the center is less than or equal to the radius
        return distanceSquared <= squaredRadius;
    }

    /**
     * Creates and returns a clone of this Circle object.
     * The clone has the same name, paint, size and center coordinates as the original.
     *
     * @return a clone of this instance.
     */
    @Override
    public ShapeTemplate clone() {
        return new Circle(this.name, this.paint, this.size, this.center);
    }
    @Override
    protected double getHeight() {
        return size;
    }
}

