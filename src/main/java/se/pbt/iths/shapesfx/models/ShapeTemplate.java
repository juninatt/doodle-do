package se.pbt.iths.shapesfx.models;

import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import se.pbt.iths.shapesfx.interfaces.Drawable;

public abstract class ShapeTemplate extends Shape implements Drawable {
    protected String name;
    protected Paint paint;
    protected double size;
    protected Paint strokeColor;
    protected double rotation;
    protected boolean selected;
    protected double cx; // Center x
    protected double cy; // Center y

    protected SVGPath svgPathData;

    public ShapeTemplate() {
    }

    public ShapeTemplate(SVGPath svgPathData) {
        this.svgPathData = svgPathData;
    }

    public ShapeTemplate(String name, Paint paint, double size) {
        this.name = name;
        this.paint = paint;
        this.size = size;
    }

    public ShapeTemplate(String name, Paint paint, Paint strokeColor, double rotation, boolean selected, double cx, double cy) {
        this.name = name;
        this.paint = paint;
        this.strokeColor = strokeColor;
        this.rotation = rotation;
        this.selected = selected;
        this.cx = cx;
        this.cy = cy;
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

    public final double getRotation() {
        return rotation;
    }

    public final void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public final boolean isSelected() {
        return selected;
    }

    public final void setSelected(boolean selected) {
        this.selected = selected;
    }

    public final double getCx() {
        return cx;
    }

    public final void setCx(double cx) {
        this.cx = cx;
    }

    public final double getCy() {
        return cy;
    }

    public final void setCy(double cy) {
        this.cy = cy;
    }

    public SVGPath getSvgPathData() {
        return svgPathData;
    }

    public void setSvgPathData(SVGPath svgPathData) {
        this.svgPathData = svgPathData;
    }
}
