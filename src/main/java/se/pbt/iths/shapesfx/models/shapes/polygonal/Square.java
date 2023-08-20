package se.pbt.iths.shapesfx.models.shapes.polygonal;

import javafx.scene.paint.Paint;
import se.pbt.iths.shapesfx.interfaces.Rotatable;
import se.pbt.iths.shapesfx.models.shapes.ShapeTemplate;

/**
 * Represents a square shape. This class extends the {@link VertexBasedShape}
 * and provides specific implementations for setting the vertices of the shape and point containment.
 *
 * @see ShapeTemplate
 */
public class Square extends VertexBasedShape implements Rotatable {

    /**
     * Constructor to initialize the square with the essential properties.
     *
     * @param name  The name of the square.
     * @param paint The fill color of the square.
     * @param size  The side length of the square.
     */
    public Square(String name, Paint paint, double size) {
        super(name, paint, size, 4);
    }


    /**
     * Determines if the specified point (x,y) is within the square's boundaries.
     *
     * @param x The X-coordinate of the point.
     * @param y The Y-coordinate of the point.
     * @return True if the point is inside the square, otherwise false.
     */
    @Override
    public boolean contains(double x, double y) {
        var leftX = centerX - size / 2;
        var topY = centerY - size / 2;
        // Check if the point lies within the horizontal and vertical boundaries of the square
        return x >= leftX && x <= leftX + size && y >= topY && y <= topY + size;
    }

    /**
     * Initializes the vertices of the square by calculating the positions of the four corners of the square
     * based on the given center coordinates and storing them in the vertices array.
     *
     * @param centerX The x-coordinate of the center of the square.
     * @param centerY The y-coordinate of the center of the square.
     */
    @Override
    public void calculateVertices(double centerX, double centerY) {
        vertices = new double[2][NUM_VERTICES];
        double halfSize = size / 2;
        double leftX = centerX - halfSize;
        double rightX = centerX + halfSize;
        double topY = centerY - halfSize;
        double bottomY = centerY + halfSize;

        // Define the X and Y coordinates for the four vertices of the square
        vertices[ROW_X] = new double[]{leftX, rightX, rightX, leftX};
        vertices[ROW_Y] = new double[]{topY, topY, bottomY, bottomY};
    }

    /**
     * Creates and returns a clone of this Square object.
     * The clone has the same name, paint, size, center coordinates,
     * and vertices as the original. If vertices is null new points will be calculated.
     *
     * @return a clone of this instance.
     */
    @Override
    public Square clone() {
        Square cloned = new Square(name, paint, size);
        cloned.setCenterX(centerX);
        cloned.setCenterY(centerY);
        if (vertices == null)
            cloned.calculateVertices(centerX, centerY);
        else
            cloned.setVertices(this.cloneVertices());
        return cloned;
    }

    /**
     * Returns the height of the square
     *
     * @return The squares height
     */
    @Override
    protected double getHeight() {
        return size;
    }
}

