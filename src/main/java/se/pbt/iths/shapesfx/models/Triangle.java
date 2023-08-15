package se.pbt.iths.shapesfx.models;

import javafx.scene.paint.Paint;
import se.pbt.iths.shapesfx.interfaces.Rotatable;

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
     * Determines if the specified point (x, y) lies within the boundaries of the triangle.
     *
     * @param coordinateX The x-coordinate of the point.
     * @param coordinateY The y-coordinate of the point.
     * @return True if the point is within the triangle otherwise false.
     */
    @Override
    public boolean contains(double coordinateX, double coordinateY) {
        double height = getHeight();

        // Translate the point to the coordinate system where the triangle is centered at the origin.
        coordinateX -= getCenterX();
        coordinateY -= getCenterY();

        // Define the horizontal and vertical boundaries of the triangle.
        double horizontalBoundary = size / 2;
        double verticalBoundary = height / 2;

        // Check if the point lies within the horizontal and vertical boundaries of the triangle.
        return (coordinateX >= -horizontalBoundary) && (coordinateX <= horizontalBoundary)
                && (coordinateY >= -verticalBoundary) && (coordinateY <= verticalBoundary);
    }

    /**
     * Initializes the vertices of the triangle by calculating the positions of the three corners of the triangle
     * based on the center coordinates and storing them in the vertices array.
     *
     * @param centerX The x-coordinate of the center of the triangle.
     * @param centerY The y-coordinate of the center of the triangle.
     */
    @Override
    protected void setVertices(double centerX, double centerY) {
        vertices = new double[2][NUM_VERTICES];

        double bottomLeftX = centerX - size / 2;
        double bottomRightX = centerX + size / 2;
        double bottomY = centerY + getHeight() / 3;
        double topY = centerY - 2 * getHeight() / 3;

        // Define the X and Y coordinates for the three vertices of the triangle
        vertices[ROW_X] = new double[]{bottomLeftX, bottomRightX, centerX};
        vertices[ROW_Y] = new double[]{bottomY, bottomY, topY};
    }

    /**
     * Calculates and returns the height of the triangle
     *
     * @return The triangles height
     */
    @Override
    protected double getHeight() {
        return (Math.sqrt(3) / 2) * size;
    }
}
