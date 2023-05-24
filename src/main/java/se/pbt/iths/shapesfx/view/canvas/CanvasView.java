package se.pbt.iths.shapesfx.view.canvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import se.pbt.iths.shapesfx.exceptions.DrawingException;
import se.pbt.iths.shapesfx.model.shapes.MyCircle;
import se.pbt.iths.shapesfx.model.shapes.MySquare;
import se.pbt.iths.shapesfx.model.shapes.MyTriangle;
import se.pbt.iths.shapesfx.model.viewmodel.CanvasViewModel;


public class CanvasView extends BorderPane {

    private final Canvas canvasNode;

    private final CanvasViewModel canvasViewModel;

    public CanvasView() {
        canvasNode = new Canvas(600, 400);
        canvasViewModel = new CanvasViewModel(canvasNode.getGraphicsContext2D());
        setCenter(canvasNode);
    }

    public void drawSquare(MySquare square, double x, double y) {
        try {
            canvasViewModel.drawSquare(square, x, y);
        } catch (DrawingException drawingException) {
            throw new RuntimeException("Failed to draw square in " + this.getClass() + ". " + drawingException.getMessage());
        }
    }

    public void drawCircle(MyCircle circle, double x, double y) {
        try {
            canvasViewModel.drawCircle(circle, x, y);
        } catch (DrawingException drawingException) {
            throw new RuntimeException("Failed to draw circle in " + this.getClass() + ". " + drawingException.getMessage());
        }
    }

    public void drawTriangle(MyTriangle triangle, double[] xPoints, double[] yPoints) {
        try {
            canvasViewModel.drawTriangle(triangle, xPoints, yPoints);
        } catch (DrawingException drawingException) {
            throw new RuntimeException("Failed to draw triangle in " + this.getClass() + ". " + drawingException.getMessage());
        }
    }

    public Canvas getCanvasNode() {
        return canvasNode;
    }
}

