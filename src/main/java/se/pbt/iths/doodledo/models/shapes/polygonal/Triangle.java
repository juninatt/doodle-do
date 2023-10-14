package se.pbt.iths.doodledo.models.shapes.polygonal;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;
import se.pbt.iths.doodledo.interfaces.Rotatable;
import se.pbt.iths.doodledo.models.shapes.ShapeTemplate;

/**
 * Represents an equilateral triangle shape. This class extends the {@link VertexBasedShape}
 * and provides specific implementations for setting the vertices of the shape and point containment.
 *
 * @see ShapeTemplate
 */
public class Triangle extends VertexBasedShape implements Rotatable {

    /**
     * Constructs an equilateral triangle with the essential properties.
     *
     * @param name  The name of the triangle.
     * @param paint The paint used to fill the triangle when drawn.
     * @param size  The side length of the equilateral triangle.
     */
    public Triangle(String name, Paint paint, double size) {
        super(name, paint, size, 3);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Point2D point) {
        double determinant1 = (vertices[ROW_Y][1] - vertices[ROW_Y][0]) * (point.getX() - vertices[ROW_X][0]) -
                (vertices[ROW_X][1] - vertices[ROW_X][0]) * (point.getY() - vertices[ROW_Y][0]);
        double determinant2 = (vertices[ROW_Y][2] - vertices[ROW_Y][1]) * (point.getX() - vertices[ROW_X][1]) -
                (vertices[ROW_X][2] - vertices[ROW_X][1]) * (point.getY() - vertices[ROW_Y][1]);
        double determinant3 = (vertices[ROW_Y][0] - vertices[ROW_Y][2]) * (point.getX() - vertices[ROW_X][2]) -
                (vertices[ROW_X][0] - vertices[ROW_X][2]) * (point.getY() - vertices[ROW_Y][2]);

        // Check if the point is inside all half-planes
        return determinant1 >= 0 && determinant2 >= 0 && determinant3 >= 0;
    }

    /**
     * Initializes the vertices of the triangle by calculating the positions of the three corners of the triangle
     * based on the center coordinates and storing them in the vertices array.
     *
     * @param center The center of the shape.
     */
    @Override
    public void calculateVertices(Point2D center) {
        vertices = new double[2][verticesSize];

        double bottomLeftX = center.getX() - size / 2;
        double bottomRightX = center.getX() + size / 2;
        double bottomY = center.getY() + getHeight() / 3;
        double topY = center.getY() - 2 * getHeight() / 3;

        // Define the X and Y coordinates for the three vertices of the triangle
        vertices[ROW_X] = new double[]{bottomLeftX, bottomRightX, center.getX()};
        vertices[ROW_Y] = new double[]{bottomY, bottomY, topY};
    }

    /**
     * Creates and returns a clone of this Triangle object.
     * The clone has the same name, paint, size, center coordinates,
     * and vertices as the original.
     *
     * @return a clone of this instance.
     */
    @Override
    public Triangle clone() {
        var clone = new Triangle(name, paint, size);
        clone.setCenter(center);
        if (vertices == null)
            clone.calculateVertices(center);
        else {
            clone.setVertices(cloneVertices());
        }
        return clone;
    }

    /**
     * Calculates and returns the height of the triangle
     *
     * @return The triangles height
     */
    @Override
    public double getHeight() {
        return (Math.sqrt(3) / 2) * size;
    }
}
