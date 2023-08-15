package se.pbt.iths.shapesfx.controller.manager;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import se.pbt.iths.shapesfx.models.ShapeTemplate;
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapeStorage;
import se.pbt.iths.shapesfx.ui.views.canvas.CanvasView;

import java.util.Optional;

/**
 * Manages canvas operations such as drawing, redrawing, and clearing shapes.
 */
public class CanvasManager {

    CanvasView canvas;
    GraphicsContext graphicsContext;

    /**
     * Constructor initializing the canvas and its GraphicsContext.
     *
     * @param canvas The canvas view.
     */
    public CanvasManager(CanvasView canvas) {
        this.canvas = canvas;
        this.graphicsContext = canvas.getCanvasNode().getGraphicsContext2D();
    }

    /**
     * Finds the first shape at the clicked point on the canvas.
     *
     * @param event The mouse event containing the click coordinates.
     * @return The first shape found at the click point, or empty if no shape is found.
     */
    public Optional<ShapeTemplate> findFirstShapeAtClickPoint(MouseEvent event) {
        return DrawnShapeStorage.getInstance().getDrawnShapes().stream()
                .filter(shape -> shape.contains(event.getX(), event.getY()))
                .findFirst();
    }

    /**
     * Redraws all the shapes on the canvas stored in {@link DrawnShapeStorage}.
     */
    public void redrawShapes() {
        DrawnShapeStorage.getInstance().getDrawnShapes()
                .forEach(shapeTemplate -> performDraw(shapeTemplate.getCenterX(), shapeTemplate.getCenterY(), shapeTemplate));
    }

    /**
     * Draws the specified shape on the canvas with the center point at the given coordinates.
     *
     * @param centerX    The x-coordinate of the center.
     * @param centerY    The y-coordinate of the center.
     * @param shape The shape to be drawn.
     */
    public void performDraw(double centerX, double centerY, ShapeTemplate shape) {
        try {
            var gc = canvas.getCanvasNode().getGraphicsContext2D();
            gc.setFill(shape.getFill());
            shape.draw(gc, centerX, centerY);
            shape.setCenterX(centerX);
            shape.setCenterY(centerY);
        } catch (RuntimeException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Clears the entire canvas.
     */
    public void clearCanvas() {
        graphicsContext.clearRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());
    }
}
