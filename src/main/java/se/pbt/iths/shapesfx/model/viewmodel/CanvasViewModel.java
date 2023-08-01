package se.pbt.iths.shapesfx.model.viewmodel;


import javafx.scene.canvas.GraphicsContext;
import se.pbt.iths.shapesfx.exceptions.DrawingException;
import se.pbt.iths.shapesfx.model.shapes.MyCircle;
import se.pbt.iths.shapesfx.model.shapes.MySquare;
import se.pbt.iths.shapesfx.model.shapes.MyTriangle;


public class CanvasViewModel {

    private final GraphicsContext graphicsContext;

    public CanvasViewModel(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public void drawSquare(MySquare square, double x, double y) throws DrawingException {

        if (square == null || graphicsContext == null) {
            throw new NullPointerException("Square or graphics context is null in " + this.getClass());
        }
        if (square.getWidth() < 0 || square.getHeight() < 0) {
            throw new IllegalArgumentException("Invalid square dimensions in " + this.getClass());
        }

        var sideLength = square.getWidth();
        var centerX = x - sideLength / 2;
        var centerY = y - sideLength / 2;

        try {
            graphicsContext.setFill(square.getPaint());
            graphicsContext.fillRect(centerX, centerY, square.getWidth(), square.getHeight());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new DrawingException("Error occurred when creating Square: " + square + ". " + illegalArgumentException.getMessage());
        }
    }

    public void drawCircle(MyCircle circle, double x, double y) throws DrawingException {

        if (circle == null || graphicsContext == null)
            throw new NullPointerException("Circle or graphics context is null in " + this.getClass());

        try {
            graphicsContext.setFill(circle.getPaint());
            graphicsContext.fillOval(x - circle.getRadius() / 2, y - circle.getRadius() / 2, circle.getRadius(), circle.getRadius());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new DrawingException("Error occurred when creating Circle: " + circle + ". " + illegalArgumentException.getMessage());
        }
    }

    public void drawTriangle(MyTriangle triangle, double[] xPoints, double[] yPoints) throws DrawingException{

        if (triangle == null || graphicsContext == null)
            throw new NullPointerException("Triangle or graphics context is null in " + this.getClass());

        try {
            graphicsContext.setFill(triangle.getPaint());
            graphicsContext.fillPolygon(xPoints, yPoints, 3);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new DrawingException("Error occurred when creating Triangle: " + triangle + ". " + illegalArgumentException.getMessage());
        }
    }

    public void clearCanvas(double width, double height) {
        graphicsContext.clearRect(0, 0, width, height);
    }
}

