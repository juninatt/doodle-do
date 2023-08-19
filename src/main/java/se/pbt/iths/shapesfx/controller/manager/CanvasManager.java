package se.pbt.iths.shapesfx.controller.manager;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import se.pbt.iths.shapesfx.models.shapes.ShapeTemplate;
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapeStorage;
import se.pbt.iths.shapesfx.ui.views.canvas.CanvasView;

import java.util.Optional;

/**
 * Manages canvas operations such as drawing, redrawing, and clearing shapes.
 */
public class CanvasManager {

    private final CanvasView canvas;
    private final GraphicsContext graphicsContext;

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
     * Draws the specified shape on the canvas with the center point at the given coordinates.
     *
     * @param centerX    The x-coordinate of the center.
     * @param centerY    The y-coordinate of the center.
     * @param shape The shape to be drawn.
     */
    public void performDraw(double centerX, double centerY, ShapeTemplate shape) {
        if (shape == null) {
            throw new IllegalArgumentException("Shape must not be null");
        }
        try {
            graphicsContext.setFill(shape.getFill());
            shape.draw(graphicsContext, centerX, centerY);
        } catch (RuntimeException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Removes the specified shape from {@link DrawnShapeStorage}.
     *
     * @param shape The shape template to be removed. Must not be null.
     * @throws IllegalArgumentException If the provided shape is null.
     */
    public void removeShape(ShapeTemplate shape) {
        if (shape == null) {
            throw new IllegalArgumentException("Shape must not be null");
        }
        DrawnShapeStorage.getInstance().removeShape(shape);
    }

    /**
     * Refreshes canvas by first clearing it and then redrawing all the shapes
     * stored in {@link DrawnShapeStorage}.
     */
    public void refreshCanvas() {
        clearCanvas();
        redrawShapes();
    }

    /**
     * Redraws all the shapes on the canvas stored in {@link DrawnShapeStorage}.
     */
    private void redrawShapes() {
        DrawnShapeStorage.getInstance().getDrawnShapes()
                .forEach(shapeTemplate -> performDraw(shapeTemplate.getCenterX(), shapeTemplate.getCenterY(), shapeTemplate));
    }

    /**
     * Clears the entire canvas.
     */
    private void clearCanvas() {
        graphicsContext.clearRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());
    }
}
