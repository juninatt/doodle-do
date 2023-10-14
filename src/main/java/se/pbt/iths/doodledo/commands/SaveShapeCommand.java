package se.pbt.iths.doodledo.commands;

import javafx.scene.input.MouseEvent;
import se.pbt.iths.doodledo.controller.manager.CanvasManager;
import se.pbt.iths.doodledo.enums.CommandType;
import se.pbt.iths.doodledo.exceptions.ShapeSaveException;
import se.pbt.iths.doodledo.interfaces.CanvasCommand;
import se.pbt.iths.doodledo.operations.ShapeSaver;
import se.pbt.iths.doodledo.ui.resources.AppMessages;
import se.pbt.iths.doodledo.ui.utils.CommandTypeProvider;
import se.pbt.iths.doodledo.ui.utils.InformationTextProvider;

/**
 * Command class responsible for saving a shape on the canvas as an SVG file.
 * Implements the {@link CanvasCommand} interface for executing the save operation.
 * Undo and redo operations are not supported for this command and will result in a message indicating inability.
 */
public class SaveShapeCommand implements CanvasCommand {

    private final CanvasManager canvasManager;
    private final ShapeSaver shapeSaver;

    /**
     * Constructs a new SaveShapeCommand with the given {@link CanvasManager} and {@link ShapeSaver}.
     *
     * @param canvasManager The manager that handles shape interactions on the canvas.
     * @param shapeSaver The utility for saving shapes in SVG format.
     */
    public SaveShapeCommand(CanvasManager canvasManager, ShapeSaver shapeSaver) {
        this.canvasManager = canvasManager;
        this.shapeSaver = shapeSaver;
    }

    /**
     * Attempts to save the shape at the clicked point on the canvas as an SVG file.
     * If no shape is found or the save operation fails, appropriate messages are displayed.
     *
     * @param event The MouseEvent indicating where the user clicked on the canvas.
     */
    @Override
    public void execute(MouseEvent event) {
        var shapeToSave = canvasManager.findFirstShapeAtClickPoint(event);
        if (shapeToSave.isEmpty()) {
            setInformationText(AppMessages.NO_SHAPE_SELECTED_MSG);
        } else {
            try {
                boolean success = shapeSaver.saveShapeAsSVG(shapeToSave.get());
                if (success) {
                    setInformationText(AppMessages.SVG_SAVE_SUCCESS_MSG);
                } else {
                    setInformationText(AppMessages.SVG_SAVE_CANCELLED_MSG);
                }
            } catch (ShapeSaveException shapeSaveException) {
                setInformationText(AppMessages.SVG_SAVE_ERROR_MSG);
            }
        }
        CommandTypeProvider.setCommandType(CommandType.EMPTY);
    }


    /**
     * Helper method to set the information text message.
     *
     * @param message The message to be displayed.
     */
    private void setInformationText(String message) {
        InformationTextProvider.setMessage(message);
    }

    /**
     * This method does not support undo functionality and will display a message indicating inability.
     */
    @Override
    public void undo() {
        InformationTextProvider.setMessage(AppMessages.UNABLE_TO_UNDO_MSG);
    }


    /**
     * This method does not support redo functionality and will display a message indicating inability.
     */
    @Override
    public void redo() {
        InformationTextProvider.setMessage(AppMessages.UNABLE_TO_REDO_MSG);
    }
}
