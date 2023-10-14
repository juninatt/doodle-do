package se.pbt.iths.doodledo.models.shapes;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;
import se.pbt.iths.doodledo.interfaces.Drawable;
import se.pbt.iths.doodledo.interfaces.Updatable;

/**
 * Abstract class representing a template for shapes with extended properties.
 * Each shape derived from this template can be drawn and updated.
 */
public abstract class ShapeTemplate implements Drawable, Updatable {
    protected String name;
    protected Paint paint;
    protected double size;
    /**
     * The x and y coordinates representing the center of the shape
     */
    protected Point2D center;

    /**
     * Default constructor.
     */
    public ShapeTemplate() {
    }

    /**
    * Constructor with essential shape properties.
    */
    public ShapeTemplate(String name, Paint paint, double size) {
        this.name = name;
        this.paint = paint;
        this.size = size;
    }

    /**
     * Comprehensive constructor with extended properties.
     */
    public ShapeTemplate(String name, Paint paint, Point2D center) {
        this.name = name;
        this.paint = paint;
        this.center = center;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Point2D getCenter() {
        return center;
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }

    /**
     * Converts the shape properties to an SVG path representation.
     *
     * @return SVG path as string.
     */
    public abstract String toSvgPath();

    /**
     * Calculates and returns the height of the shape.
     *
     * @return The height of the shape.
     */
    protected abstract double getHeight();

    /**
     * Checks if the specified point is contained within the geometric shape.
     *
     * @param coordinates the coordinates of the point to be checked
     * @return {@code true} if the point is contained within the shape, {@code false} otherwise
     */
    public abstract boolean contains(Point2D coordinates);

    /**
     * Clones the current object, creating a deep copy.
     *
     * @return A cloned instance of the ShapeTemplate.
     */
    public abstract ShapeTemplate clone();

    /**
     * Updates essential shape properties.
     *
     * @param updatingValues The {@link ShapeTemplate} object holding the new values.
     */
    @Override
    public void update(ShapeTemplate updatingValues) {
        this.name = updatingValues.getName();
        this.paint = updatingValues.getPaint();
        this.size = updatingValues.getSize();
        this.center = updatingValues.getCenter();
    }
}
