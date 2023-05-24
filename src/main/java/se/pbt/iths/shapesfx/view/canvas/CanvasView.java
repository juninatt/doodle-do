package se.pbt.iths.shapesfx.view.canvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import se.pbt.iths.shapesfx.model.shapes.MyCircle;
import se.pbt.iths.shapesfx.model.shapes.MySquare;
import se.pbt.iths.shapesfx.model.shapes.MyTriangle;
import se.pbt.iths.shapesfx.model.viewmodel.CanvasViewModel;


public class CanvasView extends BorderPane {

    private final Canvas canvasNode;
    private final GraphicsContext graphicsContext;

    private final CanvasViewModel viewModel;

    public CanvasView() {
        canvasNode = new Canvas(600, 400);
        graphicsContext = canvasNode.getGraphicsContext2D();
        viewModel = new CanvasViewModel(graphicsContext);
        setCenter(canvasNode);
    }

    public void drawSquare(MySquare square, double x, double y) {
        viewModel.drawSquare(square, x, y);
    }

    public void drawCircle(MyCircle circle, double x, double y) {
        viewModel.drawCircle(circle, x, y);
    }

    public void drawTriangle(MyTriangle triangle, double[] xPoints, double[] yPoints) {
        viewModel.drawTriangle(triangle, xPoints, yPoints);
    }


    public void clearCanvas() {
        graphicsContext.clearRect(0, 0, canvasNode.getWidth(), canvasNode.getHeight());
    }

    public Canvas getCanvasNode() {
        return canvasNode;
    }
}

