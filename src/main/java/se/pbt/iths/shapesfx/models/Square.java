package se.pbt.iths.shapesfx.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents a square shape. This class extends the {@link ShapeTemplate}
 * and provides specific implementations for drawing, SVG path conversion, and point containment
 * related to squares.
 *
 * @see ShapeTemplate
 */
public class Square extends ShapeTemplate {

    /**
     * Constructor to initialize the square with the essential properties.
     *
     * @param name       The name of the square.
     * @param paint      The fill color of the square.
     * @param sideLength The side length of the square.
     */
    public Square(String name, Paint paint, double sideLength) {
        super(name, paint, sideLength);
    }

    /**
     * Converts the square's properties to an SVG path representation.
     *
     * @return SVG path string representing the square.
     */
    @Override
    public String toSvgPath() {
        var halfSize = size / 2;
        return String.format("M %f %f L %f %f L %f %f L %f %f Z", cx - halfSize, cy - halfSize, cx - halfSize, cy + halfSize, cx + halfSize, cy + halfSize, cx + halfSize, cy - halfSize);
    }

    /**
     * Draws the square on a graphics context.
     *
     * @param gc The graphics context where the square is to be drawn.
     * @param x  The X-coordinate of the starting point.
     * @param y  The Y-coordinate of the starting point.
     */
    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        cx = x - size / 2;
        cy = y - size / 2;
        gc.setFill(getPaint());
        gc.fillRect(cx, cy, size, size);
    }

    /**
     * Determines if a point (x,y) is within the square's boundaries.
     *
     * @param x The X-coordinate of the point.
     * @param y The Y-coordinate of the point.
     * @return True if the point is inside the square, otherwise false.
     */
    @Override
    public boolean contains(double x, double y) {
        var leftX = cx - size / 2;
        var topY = cy - size / 2;
        return x >= leftX && x <= leftX + size && y >= topY && y <= topY + size;
    }
}

