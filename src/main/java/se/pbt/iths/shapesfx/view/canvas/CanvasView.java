package se.pbt.iths.shapesfx.view.canvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;


public class CanvasView extends BorderPane {

    private final Canvas canvasNode;

    public CanvasView() {
        canvasNode = new Canvas(600, 400);
        setCenter(canvasNode);
    }

    public Canvas getCanvasNode() {
        return canvasNode;
    }
}

