package se.pbt.iths.shapesfx.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class CanvasView extends BorderPane {

    private final Canvas canvas;
    private final GraphicsContext gc;

    public CanvasView() {
        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();
        setCenter(canvas);
    }

    public void drawRectangle(double x, double y, double width, double height, Color color) {
        gc.setFill(color);
        gc.fillRect(x, y, width, height);
    }

    public void drawCircle(double x, double y, double width, double height, Color color) {
        gc.setFill(color);
        gc.fillOval(x, y, width, height);
    }

    public void drawTriangle(double x, double y, double width, double height, Color color) {
        gc.setFill(color);
        gc.fillRect(x, y, width, height);
    }


    public void clearCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public Canvas getCanvas() {
        return canvas;
    }
}

