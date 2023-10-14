package se.pbt.iths.doodledo.commands;

import javafx.scene.input.MouseEvent;
import se.pbt.iths.doodledo.controller.manager.CanvasManager;
import se.pbt.iths.doodledo.interfaces.CanvasCommand;
import se.pbt.iths.doodledo.interfaces.Rotatable;
import se.pbt.iths.doodledo.models.shapes.polygonal.VertexBasedShape;
import se.pbt.iths.doodledo.ui.resources.AppMessages;
import se.pbt.iths.doodledo.ui.utils.InformationTextProvider;
import se.pbt.iths.doodledo.utils.ShapeRotator;

/**
 * Command class responsible for rotating a vertex-based shape within the canvas.
 * Implements the {@link CanvasCommand} interface for executing, undoing, and redoing the rotation operation.
 */
public class RotateShapeCommand implements CanvasCommand {

    private final CanvasManager canvasManager;
    private final ShapeRotator shapeRotator;
    private final double angle;
    private VertexBasedShape previousShape;

    /**
     * Constructs a new RotateShapeCommand utilizing the provided {@link CanvasManager} for canvas-specific operations,
     * a {@link ShapeRotator} utility for rotation logic, and an angle to determine the degree of rotation.
     *
     * @param canvasManager The canvas manager responsible for managing and manipulating the canvas where shapes are drawn.
     * @param shapeRotator  The shape rotator utility used to perform the specific rotation transformations on the shape.
     * @param angle         The angle, in degrees, by which to rotate the shape. Positive values rotate clockwise, and negative values rotate counterclockwise.
     */
    public RotateShapeCommand(CanvasManager canvasManager, ShapeRotator shapeRotator, double angle) {
        this.canvasManager = canvasManager;
        this.shapeRotator = shapeRotator;
        this.angle = angle;
    }


    /**
     * Executes the rotate command on the shape clicked on the canvas, rotating it by the specified angle.
     * If the clicked shape implements {@link Rotatable}, the rotation is performed,
     * and the canvas is refreshed. If no shape is found, an informational message is displayed.
     *
     * @param event The mouse event that triggered the command.
     */
    @Override
    public void execute(MouseEvent event) {
        var clickedShape = canvasManager.findFirstShapeAtClickPoint(event);

        clickedShape.ifPresentOrElse(
                shape -> {
                    if (shape instanceof VertexBasedShape rotatableShape) {
                        previousShape = rotatableShape;
                        rotatableShape.rotate(shapeRotator, angle);
                        canvasManager.refreshCanvas();
                    }
                },
                () -> InformationTextProvider.setMessage(AppMessages.NO_SHAPE_SELECTED_MSG)
        );
    }

    /**
     * Undoes the rotation by rotating the shape by the negative of the specified angle.
     */
    @Override
    public void undo() {
        if (previousShape != null) {
            previousShape.rotate(shapeRotator, - angle);
            canvasManager.refreshCanvas();
        }
    }

    /**
     * Redoes the rotation by reapplying the specified angle.
     */
    @Override
    public void redo() {
            if (previousShape != null) {
                previousShape.rotate(shapeRotator, angle);
                canvasManager.getShapeByName(previousShape.getName()).update(previousShape);
                canvasManager.refreshCanvas();
        }
    }
}
