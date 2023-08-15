package se.pbt.iths.shapesfx.models;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import se.pbt.iths.shapesfx.interfaces.Drawable;
import se.pbt.iths.shapesfx.interfaces.Updatable;

/**
 * Abstract class representing a template for shapes with extended properties.
 * Each shape derived from this template can be drawn and updated.
 */
public abstract class ShapeTemplate extends Shape implements Drawable, Updatable {
    protected String name;
    protected Paint paint;
    protected double size;
    protected Paint strokeColor;
    protected double centerX;
    protected double centerY;

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
    public ShapeTemplate(String name, Paint paint, Paint strokeColor, double centerX, double centerY) {
        this.name = name;
        this.paint = paint;
        this.strokeColor = strokeColor;
        this.centerX = centerX;
        this.centerY = centerY;
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

    public Paint getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Paint strokeColor) {
        this.strokeColor = strokeColor;
    }

    public final double getCenterX() {
        return centerX;
    }

    public final void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public final double getCenterY() {
        return centerY;
    }

    public final void setCenterY(double centerY) {
        this.centerY = centerY;
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
     * Updates essential shape properties.
     *
     * @param name  Updated name of the shape.
     * @param paint Updated fill color of the shape.
     * @param size  Updated size or radius of the shape.
     */
    @Override
    public void update(String name, Paint paint, double size) {
        this.name = name;
        this.paint = paint;
        this.size = size;
    }
}
