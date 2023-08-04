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
        gc.setFill(getPaint());
        gc.fillOval(x - getRadius() / 2, y - getRadius() / 2, getRadius(), getRadius());
    }
}

