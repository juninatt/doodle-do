package se.pbt.iths.shapesfx.controller.manager;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import se.pbt.iths.shapesfx.models.ShapeTemplate;
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapeStorage;
import se.pbt.iths.shapesfx.view.canvas.CanvasView;

import java.util.Optional;

public class CanvasManager {

    CanvasView canvas;

    public CanvasManager(CanvasView canvas) {
        this.canvas = canvas;
    }

    public Optional<ShapeTemplate> findFirstShapeAtClickPoint(MouseEvent event) {
        return DrawnShapeStorage.getInstance().getDrawnShapes().stream()
                .filter(shape -> shape.contains(event.getX(), event.getY()))
                .findFirst();
    }

    public void redrawShapes() {
        DrawnShapeStorage.getInstance().getDrawnShapes()
                .forEach(shapeTemplate -> performDraw(shapeTemplate.getCx(), shapeTemplate.getCy(), shapeTemplate));
    }

    public void performDraw(double x, double y, ShapeTemplate shape) {
        try {
            var gc = canvas.getCanvasNode().getGraphicsContext2D();
            gc.setFill(shape.getFill());
            shape.draw(gc, x, y);
            shape.setCx(x);
            shape.setCy(y);
        } catch (RuntimeException exception) {
            exception.printStackTrace();
        }
    }

    public void clearCanvas() {
        GraphicsContext gc = canvas.getCanvasNode().getGraphicsContext2D();
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }
}
