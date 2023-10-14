package se.pbt.iths.doodledo.models.shapes.polygonal;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import se.pbt.iths.doodledo.interfaces.Rotatable;
import se.pbt.iths.doodledo.models.shapes.ShapeTemplate;
import se.pbt.iths.doodledo.utils.ShapeRotator;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * An abstract class representing a vertex-based shape that extends the functionality of {@link ShapeTemplate}.
 * Provides common methods for drawing, rotating, and converting the shape to an SVG path.
 */
public abstract class VertexBasedShape extends ShapeTemplate implements Rotatable {

    // Fields
    protected final int verticesSize;
    protected double[][] vertices;


    /**
     * Constructs a vertex-based shape with the essential properties.
     *
     * @param name        The name of the shape.
     * @param paint       The paint used to fill the shape when drawn.
     * @param size        The size of the shape.
     * @param numVertices The number of vertices that define the shape.
     */
    protected VertexBasedShape(String name, Paint paint, double size, int numVertices) {
        super(name, paint, size);
        verticesSize = numVertices;
    }

    // Getters and setters

    /**
     * Returns the number of vertices that define the shape.
     *
     * @return The number of vertices.
     */
    public int getVerticesSize() {
        return verticesSize;
    }

    /**
     * Returns the vertices that define the shape as a 2D array.
     *
     * @return The vertices.
     */
    public double[][] getVertices() {
        return vertices;
    }

    public void setVertices(double[][] vertices) {
        this.vertices = vertices;
    }


    /**
     * Draws the shape on the specified GraphicsContext at the given center coordinates and
     * initializes vertices if not set, then fills the polygon using the shape's paint color.
     *
     * @param gc       The GraphicsContext to draw on.
     * @param point    The center of the shape
     */
    @Override
    public void draw(GraphicsContext gc, Point2D point) {
        this.center = point;

        if (vertices == null)
            calculateVertices(center);

        gc.setFill(getPaint());
        gc.fillPolygon(vertices[ROW_X], vertices[ROW_Y], verticesSize);
    }

    /**
     * Rotates the shape by a specified angle by updating the vertices of the shape based on the given rotation angle.
     *
     * @param rotator The {@link ShapeRotator} used to perform the rotation.
     * @param angle   The angle in degrees by which the shape should be rotated.
     */
    @Override
    public void rotate(ShapeRotator rotator, double angle) {
        vertices = rotator.rotatePoints(vertices, center, angle);
    }

    /**
     * Sets the size of the shape, recalculates vertices, and updates the center point.
     *
     * @param size The new size of the shape.
     */
    @Override
    public void setSize(double size) {
        super.setSize(size);
        calculateVertices(center);
    }

    /**
     * Generates an SVG path string for the shape using the vertices by iterating through the vertices array.
     *
     * @return The SVG path string representing the shape.
     */
    @Override
    public String toSvgPath() {
        // Converting the vertices into an SVG path by looping through them with a stream
        return IntStream.range(0, verticesSize)
                .mapToObj(i -> (i == 0 ? "M" : "L") +
                        String.format(Locale.ENGLISH, " %.6f %.6f", vertices[ROW_X][i], vertices[ROW_Y][i]))
                .collect(Collectors.joining(" ")) + " Z";
    }

    /**
     * Updates the shape based on the given shape's properties.
     *
     * @param updatingValues The shape whose properties will be used for updating.
     */
    @Override
    public void update(ShapeTemplate updatingValues) {
        super.update(updatingValues);
        if (updatingValues instanceof VertexBasedShape vertexBasedUpdatingValues) {
            setVertices(vertexBasedUpdatingValues.cloneVertices());
        }
    }

    /**
     * Clones the vertices array to create a deep copy.
     *
     * @return A deep copy of the vertices array.
     */
    protected double[][] cloneVertices() {
        return Arrays.stream(vertices)
                .map(double[]::clone)
                .toArray(double[][]::new);
    }

    /**
     * Defines the shape's vertices based on the given central coordinates.
     * How these coordinates translate into vertices depends on the individual characteristics of the shape.
     *
     * @param center The center of the shape.
     */
    protected abstract void calculateVertices(Point2D center);
}
