package se.pbt.iths.shapesfx.commands;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.controller.manager.CanvasManager;
import se.pbt.iths.shapesfx.enums.CommandType;
import se.pbt.iths.shapesfx.interfaces.CanvasCommand;
import se.pbt.iths.shapesfx.models.shapes.ShapeTemplate;
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapeStorage;
import se.pbt.iths.shapesfx.modelsmanagement.SelectedShape;
import se.pbt.iths.shapesfx.ui.config.FXMLStageConfigurator;
import se.pbt.iths.shapesfx.ui.resources.AppMessages;
import se.pbt.iths.shapesfx.ui.utils.CommandTypeProvider;
import se.pbt.iths.shapesfx.ui.utils.InformationTextProvider;

/**
 * Class representing a command to edit a shape on the canvas.
 * Implements the {@link CanvasCommand} interface for executing, undoing, and redoing the edit operation.
 */
public class EditShapeCommand implements CanvasCommand {

    private final CanvasManager canvasManager;
    private ShapeTemplate originalShape;
    private ShapeTemplate editedShape;

    /**
     * Initializes a new instance of EditShapeCommand with the given {@link CanvasManager}.
     *
     * @param canvasManager The manager that handles shape interactions on the canvas.
     */
    public EditShapeCommand(CanvasManager canvasManager) {
        this.canvasManager = canvasManager;
    }

    /**
     * Executes the edit command by selecting a shape to be edited based on a click event,
     * and opening the edit view for the selected shape. If no shape is found at the click point,
     * an information message is displayed.
     *
     * @param event The MouseEvent indicating where the user clicked on the canvas.
     */
    @Override
    public void execute(MouseEvent event) {
        var shapeToEdit = canvasManager.findFirstShapeAtClickPoint(event);
        if (shapeToEdit.isEmpty())
            InformationTextProvider.setMessage(AppMessages.NO_SHAPE_SELECTED_MSG);
        else {
            originalShape = shapeToEdit.get().clone();
            editedShape = shapeToEdit.get();
            SelectedShape.getInstance().setSelectedShape(editedShape);
            var windowLoader = new FXMLStageConfigurator(new Stage());
            windowLoader.getConfiguredStage(shapeToEdit.get().getName(), "edit-shape-view.fxml").showAndWait();
            canvasManager.refreshCanvas();
            SelectedShape.getInstance().reset();
        }
        CommandTypeProvider.setCommandType(CommandType.EMPTY);
    }

    /**
     * Undoes the edit operation, reverting the shape to its original state before editing.
     */
    @Override
    public void undo() {
        if (originalShape != null && editedShape != null) {
            DrawnShapeStorage.getInstance().get(editedShape.getName())
                    .ifPresent(shape -> shape.update(originalShape));
            canvasManager.refreshCanvas();
        }
    }

    /**
     * Reverts the previous undo operation, applying the changes that were previously undone.
     */
    @Override
    public void redo() {
        if (originalShape != null && editedShape != null) {
            DrawnShapeStorage.getInstance().get(originalShape.getName())
                    .ifPresent(shape -> shape.update(editedShape));
        }
    }
}
