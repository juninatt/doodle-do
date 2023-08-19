package se.pbt.iths.shapesfx.commands;

import javafx.scene.input.MouseEvent;
import se.pbt.iths.shapesfx.controller.manager.CanvasManager;
import se.pbt.iths.shapesfx.enums.CommandType;
import se.pbt.iths.shapesfx.interfaces.CanvasCommand;
import se.pbt.iths.shapesfx.models.shapes.ShapeTemplate;
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapeStorage;
import se.pbt.iths.shapesfx.ui.resources.AppMessages;
import se.pbt.iths.shapesfx.ui.utils.CommandTypeProvider;
import se.pbt.iths.shapesfx.ui.utils.InformationTextProvider;

/**
 * Command class responsible for removing shapes from the canvas. Removes the shape at the click point and refreshes the canvas.
 * Implements the {@link CanvasCommand} interface for executing, undoing, and redoing the remove operation.
 */
public class RemoveShapeCommand implements CanvasCommand {

    private final CanvasManager canvasManager;
    private ShapeTemplate removedShape;

    /**
     *
     * Initializes a new instance of RemoveShapeCommand with the given {@link CanvasManager}.
     *
     * @param canvasManager The manager that handles shape interactions on the canvas.
     */
    public RemoveShapeCommand(CanvasManager canvasManager) {
        this.canvasManager = canvasManager;
    }

    /**
     * Executes the removal command by finding the clicked shape on the canvas and removing it.
     * If no shape is found at the clicked point, a message indicating no shape selection is displayed.
     *
     * @param event The MouseEvent indicating where the user clicked on the canvas.
     */
    @Override
    public void execute(MouseEvent event) {
        var optionalShape = canvasManager.findFirstShapeAtClickPoint(event);
        optionalShape.ifPresentOrElse(
                shape -> {
                    canvasManager.removeShape(shape);
                    InformationTextProvider.setMessage(AppMessages.SHAPE_REMOVAL_MSG + shape.getName());
                    canvasManager.refreshCanvas();
                    removedShape = shape;
                },
                () -> InformationTextProvider.setMessage(AppMessages.NO_SHAPE_SELECTED_MSG)
        );
        CommandTypeProvider.setCommandType(CommandType.EMPTY);
    }

    /**
     * Undoes the last removal operation by adding the removed shape back to the storage and refreshing the canvas.
     */
    @Override
    public void undo() {
        DrawnShapeStorage.getInstance().getDrawnShapes().add(removedShape);
        canvasManager.refreshCanvas();
    }

    /**
     * Reverts the last undo operation by removing the shape once again from the storage and refreshing the canvas.
     */
    @Override
    public void redo() {
        canvasManager.removeShape(removedShape);
        canvasManager.refreshCanvas();
    }
}
