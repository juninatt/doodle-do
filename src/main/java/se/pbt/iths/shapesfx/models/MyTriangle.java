package se.pbt.iths.shapesfx.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import se.pbt.iths.shapesfx.interfaces.Drawable;

// TODO: Move shape properties to properties object
public class MyTriangle extends Polygon implements Drawable {

    private String name;
    private Paint paint;
    private double size;
    private boolean selected;

    public MyTriangle(String name, double size, Paint paint) {
        super();
        this.name = name;
        this.size = size;
        this.paint = paint;
        this.selected = false;
        setFill(paint);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
        setFill(paint);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        // Calculate half of the triangle's height.
        // For an equilateral triangle, the height is (sqrt(3) / 2) * side length
        // We divide this by 2 to get half of the height, which is used to determine the y-coordinates of the triangle's vertices
        double halfHeight = (Math.sqrt(3) / 2) * getSize() / 2.0;

        // Calculate half of the triangle's side length.
        // This is used to determine the x-coordinates of the triangle's vertices
        double halfSide = getSize() / 2.0;

        // Define the x-coordinates of the triangle's vertices.
        // The first and second vertices are half a side length to the left and right of the triangle's center (x - halfSide, x + halfSide)
        // The third vertex is directly above the center (x)
        double[] xPoints = {x - halfSide, x + halfSide, x};

        // Define the y-coordinates of the triangle's vertices.
        // The first and second vertices are half a height below the triangle's center (y + halfHeight)
        // The third vertex is half a height above the center (y - halfHeight)
        double[] yPoints = {y + halfHeight, y + halfHeight, y - halfHeight};

        // Set the color of the triangle
        gc.setFill(getPaint());

        // Draw the triangle on the canvas using the calculated x and y coordinates for its vertices
        gc.fillPolygon(xPoints, yPoints, 3);
    }

}
