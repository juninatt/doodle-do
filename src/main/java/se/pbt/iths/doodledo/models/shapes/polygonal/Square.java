package se.pbt.iths.doodledo.models.shapes.polygonal;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;
import se.pbt.iths.doodledo.interfaces.Rotatable;
import se.pbt.iths.doodledo.models.shapes.ShapeTemplate;

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
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Point2D coordinates) {
        double x = coordinates.getX();
        double y = coordinates.getY();

        var leftX = center.getX() - size / 2;
        var topY = center.getY() - size / 2;
        // Check if the point lies within the horizontal and vertical boundaries of the square
        return coordinates.getX() >= leftX && x <= leftX + size && y >= topY && y <= topY + size;
    }

    /**
     * Initializes the vertices of the square by calculating the positions of the four corners of the square
     * based on the given center coordinates and storing them in the vertices array.
     *
     * @param center The center of the shape.
     */
    @Override
    public void calculateVertices(Point2D center) {
        vertices = new double[2][verticesSize];
        double halfSize = size / 2;
        double leftX = center.getX() - halfSize;
        double rightX = center.getX() + halfSize;
        double topY = center.getY() - halfSize;
        double bottomY = center.getY() + halfSize;

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
        cloned.setCenter(center);
        if (vertices == null)
            cloned.calculateVertices(center);
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

