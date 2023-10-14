package se.pbt.iths.doodledo.controller.manager;

import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import se.pbt.iths.doodledo.models.shapes.ShapeTemplate;
import se.pbt.iths.doodledo.modelsmanagement.DrawnShapeStorage;
import se.pbt.iths.doodledo.ui.views.canvas.CanvasView;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Manages canvas operations such as drawing, redrawing, and clearing shapes.
 */
public class CanvasManager {

    private final CanvasView canvas;
    private final GraphicsContext graphicsContext;
    private final DrawnShapeStorage drawnShapes;

    /**
     * Constructor initializing the canvas and its GraphicsContext, along with the storage of drawn shapes.
     *
     * @param canvas       The canvas view.
     * @param drawnShapes  The storage managing the collection of drawn shapes.
     */
    public CanvasManager(CanvasView canvas, DrawnShapeStorage drawnShapes) {
        this.canvas = canvas;
        this.graphicsContext = canvas.getCanvasNode().getGraphicsContext2D();
        this.drawnShapes = drawnShapes;
    }

    /**
     * Finds the first shape at the clicked point on the canvas.
     *
     * @param event The mouse event containing the click coordinates.
     * @return The first shape found at the click point, or empty if no shape is found.
     */
    public Optional<ShapeTemplate> findFirstShapeAtClickPoint(MouseEvent event) {
        return drawnShapes.getDrawnShapes().stream()
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

    public ObservableList<ShapeTemplate> getAllDrawnShapes() {
        return drawnShapes.getDrawnShapes();
    }

    public void addShape(ShapeTemplate shape) {
        drawnShapes.addShape(shape);
    }

    /**
     * Removes the specified shape from {@link DrawnShapeStorage}.
     *
     * @param shape The shape template to be removed. Must not be null.
     * @throws IllegalArgumentException If the provided shape is null.
     */
    public void removeShape(ShapeTemplate shape) {
        drawnShapes.removeShape(shape);
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
        drawnShapes.getDrawnShapes()
                .forEach(shapeTemplate -> performDraw(shapeTemplate.getCenterX(), shapeTemplate.getCenterY(), shapeTemplate));
    }

    /**
     * Clears the entire canvas.
     */
    private void clearCanvas() {
        graphicsContext.clearRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());
    }

    public ShapeTemplate getShapeByName(String name) {
        return drawnShapes.get(name).orElseThrow(() -> new NoSuchElementException("Shape with the name " + name + " not found"));
    }
}
