package se.pbt.iths.shapesfx.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Circle extends ShapeTemplate {

    private double radius;


    public Circle(String name, Paint fillColor, double radius) {
        super(name, fillColor);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        cx = x;
        cy = y;
        gc.setFill(getPaint());
        gc.fillOval(x - radius / 2, y - radius / 2, radius, radius);
    }

    @Override
    public boolean contains(double x, double y) {
        double dx = cx - x;
        double dy = cy - y;
        double distanceSquared = dx * dx + dy * dy;
        return distanceSquared <= radius * radius;
    }
}

