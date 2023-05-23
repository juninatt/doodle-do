package se.pbt.iths.shapesfx.view.canvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import se.pbt.iths.shapesfx.model.MyCircle;
import se.pbt.iths.shapesfx.model.MySquare;
import se.pbt.iths.shapesfx.model.MyTriangle;


public class CanvasView extends BorderPane {

    private final Canvas canvas;
    private final GraphicsContext gc;

    public CanvasView() {
        canvas = new Canvas(500, 300);
        gc = canvas.getGraphicsContext2D();
        setCenter(canvas);
    }

    public void drawSquare(MySquare square, double x, double y) {
        var sideLength = square.getWidth();
        var centerX = x - sideLength / 2;
        var centerY = y - sideLength / 2;
        gc.setFill(square.getPaint());
        gc.fillRect(centerX, centerY, square.getWidth(), square.getHeight());
    }

    public void drawCircle(MyCircle circle, double x, double y) {
        gc.setFill(circle.getPaint());
        gc.fillOval(x - circle.getRadius(), y - circle.getRadius(), circle.getRadius() * 2, circle.getRadius() * 2);
    }

    public void drawTriangle(MyTriangle triangle, double[] xPoints, double[] yPoints) {
        gc.setFill(triangle.getPaint());
        gc.fillPolygon(xPoints, yPoints, 3);
    }


    public void clearCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public Canvas getCanvas() {
        return canvas;
    }
}

