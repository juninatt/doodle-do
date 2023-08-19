package se.pbt.iths.shapesfx.models.shapes.nonpolygonal;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import se.pbt.iths.shapesfx.models.shapes.ShapeTemplate;

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

    public Circle(String name, Paint fillColor, double diameter, double centerX, double centerY) {
        super(name, fillColor, diameter);
        this.centerX = centerX;
        this.centerY = centerY;
    }

    /**
     * Converts the circle's properties to an SVG path representation.
     *
     * @return SVG path string representing the circle.
     */
    @Override
    public String toSvgPath() {
        var radius = getSize() / 2;
        return String.format("M %f %f m -%f, 0 a %f,%f 0 1,0 %f,0 a %f,%f 0 1,0 -%f,0", centerX, centerY, radius, radius, radius, radius * 2, radius, radius, radius * 2);
    }

    /**
     * Draws the circle on a graphics context.
     *
     * @param gc The graphics context where the circle is to be drawn.
     * @param coordinateX  The X-coordinate of the circle's center.
     * @param coordinateY  The Y-coordinate of the circle's center.
     */
    @Override
    public void draw(GraphicsContext gc, double coordinateX, double coordinateY) {
        // Set the center coordinates of the circle
        centerX = coordinateX;
        centerY = coordinateY;

        var diameter = size;

        // Draw the filled oval (circle) using the specified coordinates, radius, and fill color
        gc.setFill(getPaint());
        gc.fillOval(coordinateX - diameter / 2, coordinateY - diameter / 2, diameter, diameter);
    }

    /**
     * Determines if a point (x,y) is within the circle's boundaries.
     *
     * @param coordinateX The X-coordinate of the point.
     * @param coordinateY The Y-coordinate of the point.
     * @return True if the point is inside the circle, otherwise false.
     */
    @Override
    public boolean contains(double coordinateX, double coordinateY) {
        // Find the difference in x and y coordinates from the point to the center of the circle
        double horizontalDifference = centerX - coordinateX;
        double verticalDifference = centerY - coordinateY;

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
        return new Circle(this.name, this.paint, this.size, this.centerX, this.centerY);
    }
    @Override
    protected double getHeight() {
        return size;
    }
}

