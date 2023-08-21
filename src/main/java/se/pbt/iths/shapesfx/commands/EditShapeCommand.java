package se.pbt.iths.shapesfx.commands;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.controller.manager.CanvasManager;
import se.pbt.iths.shapesfx.enums.CommandType;
import se.pbt.iths.shapesfx.interfaces.CanvasCommand;
import se.pbt.iths.shapesfx.models.shapes.ShapeTemplate;
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
    private String shapeName;

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
        shapeToEdit.ifPresentOrElse(
                shape -> {
                    // Copy current state for undo/redo actions
                    copyState(shape);
                    SelectedShape.getInstance().setSelectedShape(shape);
                    // Open dialog window and set new properties for the editing shape
                    openEditDialog(shape);
                    // Clear canvas and redraw shapes
                    canvasManager.refreshCanvas();
                    SelectedShape.getInstance().reset();
                },
                () -> InformationTextProvider.setMessage(AppMessages.NO_SHAPE_SELECTED_MSG)
        );

        CommandTypeProvider.setCommandType(CommandType.EMPTY);
    }

    /**
     * Creates deep copies of the original shape and the shape to be edited and
     * stores the name of the original shape for later reference.
     *
     * @param shapeToEdit An Optional containing the shape template to be copied. The shape's name is also stored for later use.
     */
    private void copyState(ShapeTemplate shapeToEdit) {
        originalShape = shapeToEdit.clone();
        editedShape = shapeToEdit.clone();
        shapeName = originalShape.getName();
    }

    /**
     * Opens a dialog for editing a shape, based on the provided shape template wrapped in an Optional.
     * If the Optional is empty, this method will do nothing.
     *
     * @param shapeToEdit An Optional containing the shape template to be edited. If the Optional is empty, the dialog will not be opened.
     */
    private static void openEditDialog(ShapeTemplate shapeToEdit) {
        var windowLoader = new FXMLStageConfigurator(new Stage());
        windowLoader.getConfiguredStage(shapeToEdit.getName(), "edit-shape-view.fxml").showAndWait();
    }

    /**
     * Undoes the edit operation, reverting the shape to its original state before editing.
     */
    @Override
    public void undo() {
        updateShape(originalShape);
    }

    /**
     * Reverts the previous undo operation, applying the changes that were previously undone.
     */
    @Override
    public void redo() {
        updateShape(editedShape);
    }

    /**
     * Updates the shape on the canvas with the given shape.
     * If either the original shape or the edited shape is null, this method will do nothing.
     * After updating the shape, it refreshes the canvas to reflect the changes.
     *
     * @param updatingShape The shape template to use for updating the shape on the canvas.
     */
    private void updateShape(ShapeTemplate updatingShape) {
        if (updatingShape != null && editedShape != null) {
            canvasManager.getShapeByName(shapeName).update(updatingShape);
            canvasManager.refreshCanvas();
        }
    }
}
