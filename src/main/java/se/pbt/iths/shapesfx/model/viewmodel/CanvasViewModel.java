package se.pbt.iths.shapesfx.model.viewmodel;


import javafx.scene.canvas.GraphicsContext;
import se.pbt.iths.shapesfx.model.shapes.MyCircle;
import se.pbt.iths.shapesfx.model.shapes.MySquare;
import se.pbt.iths.shapesfx.model.shapes.MyTriangle;


public class CanvasViewModel {

    private final GraphicsContext graphicsContext;

    public CanvasViewModel(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public void drawSquare(MySquare square, double x, double y) {
        var sideLength = square.getWidth();
        var centerX = x - sideLength / 2;
        var centerY = y - sideLength / 2;
        graphicsContext.setFill(square.getPaint());
        graphicsContext.fillRect(centerX, centerY, square.getWidth(), square.getHeight());
    }

    public void drawCircle(MyCircle circle, double x, double y) {
        graphicsContext.setFill(circle.getPaint());
        graphicsContext.fillOval(x - circle.getRadius(), y - circle.getRadius(), circle.getRadius() * 2, circle.getRadius() * 2);
    }

    public void drawTriangle(MyTriangle triangle, double[] xPoints, double[] yPoints) {
        graphicsContext.setFill(triangle.getPaint());
        graphicsContext.fillPolygon(xPoints, yPoints, 3);
    }

    public void clearCanvas(double width, double height) {
        graphicsContext.clearRect(0, 0, width, height);
    }
}

