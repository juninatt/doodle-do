package se.pbt.iths.shapesfx.models.shapes.polygonal;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import se.pbt.iths.shapesfx.interfaces.Rotatable;
import se.pbt.iths.shapesfx.models.shapes.ShapeTemplate;
import se.pbt.iths.shapesfx.utils.ShapeRotator;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * An abstract class representing a vertex-based shape that extends the functionality of {@link ShapeTemplate}.
 * Provides common methods for drawing, rotating, and converting the shape to an SVG path.
 */
public abstract class VertexBasedShape extends ShapeTemplate implements Rotatable {

    // Constants
    protected final int NUM_VERTICES;

    // Instance variables
    protected double[][] vertices;

    public void setVertices(double[][] vertices) {
        this.vertices = vertices;
    }

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
        NUM_VERTICES = numVertices;
    }


    /**
     * Returns the number of vertices that define the shape.
     *
     * @return The number of vertices.
     */
    public int getNUM_VERTICES() {
        return NUM_VERTICES;
    }

    /**
     * Returns the vertices that define the shape as a 2D array.
     *
     * @return The vertices.
     */
    public double[][] getVertices() {
        return vertices;
    }

    /**
     * Draws the shape on the specified GraphicsContext at the given center coordinates and
     * initializes vertices if not set, then fills the polygon using the shape's paint color.
     *
     * @param gc       The GraphicsContext to draw on.
     * @param centerX  The x-coordinate of the center.
     * @param centerY  The y-coordinate of the center.
     */
    @Override
    public void draw(GraphicsContext gc, double centerX, double centerY) {
        this.centerX = centerX;
        this.centerY = centerY;

        // Initializing the vertices if they are not set
        if (vertices == null)
            calculateVertices(centerX, centerY);

        // Draws the shape on the canvas of the graphics context
        gc.setFill(getPaint());
        gc.fillPolygon(vertices[ROW_X], vertices[ROW_Y], NUM_VERTICES);
    }

    /**
     * Rotates the shape by a specified angle by updating the vertices of the shape based on the given rotation angle.
     *
     * @param rotator The {@link ShapeRotator} used to perform the rotation.
     * @param angle   The angle in degrees by which the shape should be rotated.
     */
    @Override
    public void rotate(ShapeRotator rotator, double angle) {
        vertices = rotator.rotatePoints(vertices, centerX, centerY, angle);
    }

    @Override
    public void setSize(double size) {
        super.setSize(size);
        calculateVertices(centerX, centerY);
    }

    /**
     * Generates an SVG path string for the shape using the vertices by iterating through the vertices array.
     *
     * @return The SVG path string representing the shape.
     */
    @Override
    public String toSvgPath() {
        // Converting the vertices into an SVG path by looping through them with a stream
        return IntStream.range(0, NUM_VERTICES)
                .mapToObj(i -> (i == 0 ? "M" : "L") + " " + vertices[ROW_X][i] + " " + vertices[ROW_Y][i])
                .collect(Collectors.joining(" ")) + " Z";
    }

    @Override
    public void update(ShapeTemplate updatingValues) {
        super.update(updatingValues);
        if (updatingValues instanceof VertexBasedShape vertexBasedUpdatingValues) {
            setVertices(vertexBasedUpdatingValues.cloneVertices());
        }
    }


    protected double[][] cloneVertices() {
        return Arrays.stream(vertices)
                .map(double[]::clone)
                .toArray(double[][]::new);
    }


    /**
     * Defines the shape's vertices based on the given central coordinates.
     * How these coordinates translate into vertices depends on the individual characteristics of the shape.
     *
     * @param centerX The horizontal midpoint of the shape.
     * @param centerY The vertical midpoint of the shape.
     */
    protected abstract void calculateVertices(double centerX, double centerY);
}
