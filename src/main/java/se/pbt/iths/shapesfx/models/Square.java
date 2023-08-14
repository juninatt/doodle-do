package se.pbt.iths.shapesfx.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import se.pbt.iths.shapesfx.interfaces.Rotatable;
import se.pbt.iths.shapesfx.utils.ShapeRotator;

/**
 * Represents a square shape. This class extends the {@link ShapeTemplate}
 * and provides specific implementations for drawing, SVG path conversion, and point containment
 * related to squares.
 *
 * @see ShapeTemplate
 */
public class Square extends ShapeTemplate implements Rotatable {

    private static final int NUM_VERTICES = 4;

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
        cx = x;
        cy = y;

        if (vertices == null)
            setVertices(x, y);

        gc.setFill(getPaint());
        gc.fillPolygon(vertices[ROW_X], vertices[ROW_Y], NUM_VERTICES);
    }

    private void setVertices(double x, double y) {
        vertices = new double[2][NUM_VERTICES];

        vertices[ROW_X] = new double[]{
                x - size / 2,  // Top-left
                x + size / 2,  // Top-right
                x + size / 2,  // Bottom-right
                x - size / 2   // Bottom-left
        };

        vertices[ROW_Y] = new double[]{
                y - size / 2,  // Top-left
                y - size / 2,  // Top-right
                y + size / 2,  // Bottom-right
                y + size / 2   // Bottom-left
        };
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

    @Override
    public void rotate(ShapeRotator rotator, double angle) {
        vertices = rotator.rotatePoints(vertices, cx, cy, angle);
    }
}

