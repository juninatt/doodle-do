package se.pbt.iths.doodledo.commands;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import se.pbt.iths.doodledo.controller.manager.CanvasManager;
import se.pbt.iths.doodledo.interfaces.CanvasCommand;
import se.pbt.iths.doodledo.models.shapes.ShapeTemplate;
import se.pbt.iths.doodledo.modelsmanagement.SelectedShape;
import se.pbt.iths.doodledo.ui.resources.AppMessages;
import se.pbt.iths.doodledo.ui.utils.InformationTextProvider;

import java.util.Optional;

/**
 * Command class responsible for drawing shapes on the canvas. It includes methods to handle
 * drawing a shape based on user interactions, and implements {@link CanvasCommand} for  undoing and redoing the draw operation.
 */
public class DrawShapeCommand implements CanvasCommand {

    CanvasManager canvasManager;
    ShapeTemplate drawnShape;

    /**
     * Initializes a new instance of DrawShapeCommand with the given {@link CanvasManager}.
     *
     * @param canvasManager The manager that handles shape interactions on the canvas.
     */
    public DrawShapeCommand(CanvasManager canvasManager) {
        this.canvasManager = canvasManager;
    }

    /**
     * Attempts to draw the currently set {@link SelectedShape} onto the canvas based on the user's click position.
     * If no shape is selected, a default instruction message is displayed. In the event of a runtime
     * exception during the drawing process, an error message is displayed and the exception is printed.
     *
     * @param event The MouseEvent indicating where the user clicked on the canvas.
     */
    @Override
    public void execute(MouseEvent event) {
            var shapeToDraw = Optional.ofNullable(SelectedShape.getInstance().getSelectedShape());

        shapeToDraw.ifPresentOrElse(shape -> {
            try {
                shape.setCenter(new Point2D(event.getX(), event.getY()));
                canvasManager.performDraw(shape);
                canvasManager.addShape(shape);
                drawnShape = shape;
                setInformationText(AppMessages.BEAUTIFUL);
                SelectedShape.getInstance().reset();
            } catch (RuntimeException runtimeException) {
                setInformationText(AppMessages.DEFAULT_INSTRUCTION_MSG);
                runtimeException.printStackTrace();
            }
        }, () -> setInformationText(AppMessages.DEFAULT_INSTRUCTION_MSG));
    }

    /**
     * Private helper method to set the information text message.
     *
     * @param message The message to be displayed.
     */
    private void setInformationText(String message) {
        InformationTextProvider.setMessage(message);
    }

    /**
     * Undoes the last draw operation by removing the drawn shape from the storage and refreshing the canvas.
     */
    @Override
    public void undo() {
        canvasManager.removeShape(drawnShape);
        canvasManager.refreshCanvas();
    }

    /**
     * Redoes the last draw operation by adding the drawn shape back to the storage and refreshing the canvas.
     */
    @Override
    public void redo() {
        if (drawnShape != null) {
            canvasManager.addShape(drawnShape);
            canvasManager.refreshCanvas();
        }
    }
}
