package se.pbt.iths.shapesfx.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.controller.manager.CanvasManager;
import se.pbt.iths.shapesfx.enums.ActionType;
import se.pbt.iths.shapesfx.exceptions.ShapeSaveException;
import se.pbt.iths.shapesfx.models.ShapeTemplate;
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapeStorage;
import se.pbt.iths.shapesfx.modelsmanagement.SelectedShape;
import se.pbt.iths.shapesfx.operations.ShapeSaver;
import se.pbt.iths.shapesfx.ui.config.AvailableShapesMenuConfigurator;
import se.pbt.iths.shapesfx.ui.config.DrawShapeMenuConfigurator;
import se.pbt.iths.shapesfx.ui.config.FXMLStageConfigurator;
import se.pbt.iths.shapesfx.ui.config.SelectMenuConfigurator;
import se.pbt.iths.shapesfx.ui.resources.AppMessages;
import se.pbt.iths.shapesfx.ui.utils.ActionTypeProvider;
import se.pbt.iths.shapesfx.ui.utils.InformationTextProvider;
import se.pbt.iths.shapesfx.ui.views.canvas.CanvasView;

// TODO: Fix case when selected shape is drawn again. Create new shape with new name?
// TODO: Improve exception handling with alert message
/**
 * The main controller class for the Shapes application.
 * Handles user interactions and shape creation/drawing.
 */
public class MainWindowController {

    private CanvasManager canvasManager;

    @FXML
    private CanvasView canvasView;
    @FXML
    private Menu drawNewShapeMenu;
    @FXML
    private Menu drawnShapesMenu;
    @FXML
    private Menu selectMenu;
    @FXML
    private Label informationText;

    public MainWindowController() {
        ActionTypeProvider.setType(ActionType.EMPTY);
    }


    /**
     * Initializes the ShapesController with its default values and required instances.
     * Binds the menu items and information text property and sets action events for items.
     */
    public void initialize() {
        setUpCanvas();
        setUpMenuBar();
        setUpInformationLabel();
    }

    // Private Helper Methods to initialize components

    /**
     * Initializes the canvas settings for the application.
     */
    private void setUpCanvas() {
        VBox.setMargin(canvasView, new Insets(10, 10, 10, 10));
        canvasManager = new CanvasManager(canvasView);
    }

    /**
     * Initializes the menu bar by setting up item actions and binding the menu to saved shapes.
     */
    private void setUpMenuBar() {
        new SelectMenuConfigurator(selectMenu)
                .configure();
        new DrawShapeMenuConfigurator(drawNewShapeMenu, new FXMLStageConfigurator(new Stage()))
                .configure();
        new AvailableShapesMenuConfigurator(drawnShapesMenu, DrawnShapeStorage.getInstance())
                .configure();
    }


    /**
     * Set a welcome message to the label and bind it to {@link InformationTextProvider}.
     */
    private void setUpInformationLabel() {
        informationText.textProperty().bind(InformationTextProvider.getMessage());
        setInformationText(AppMessages.WELCOME);
    }

    // Event handling methods

    /**
     * Handles the click event on the canvas, performing actions based on the currentAction type.
     *
     * @param event The MouseEvent representing the canvas click event.
     */
    @FXML
    private void handleCanvasClick(MouseEvent event) {
        switch (ActionTypeProvider.getType()) {
            case DRAW -> attemptDrawShape(event);
            case EDIT -> attemptEditShape(event);
            case SAVE -> attemptSaveShape(event);
            case ROTATE -> attemptRotateShape(event);
            case REMOVE -> attemptRemoveShape(event);
            case EMPTY -> setInformationText(AppMessages.DEFAULT_INSTRUCTION_MSG);
        }
    }

    // Shape handling methods

    /**
     * Attempts to edit a shape based on the user's click point. If a shape is selected, an edit view is launched;
     * otherwise, a message is displayed to inform the user that no shape was selected. After editing, the canvas
     * is refreshed to reflect any changes.
     *
     * @param event The MouseEvent indicating where the user clicked.
     */
    private void attemptEditShape(MouseEvent event) {
        var shapeToEdit = canvasManager.findFirstShapeAtClickPoint(event);
        if (shapeToEdit.isEmpty())
            setInformationText(AppMessages.NO_SHAPE_SELECTED_MSG);
        else {
            SelectedShape.getInstance().setSelectedShape(shapeToEdit.get());
            var windowLoader = new FXMLStageConfigurator(new Stage());
            windowLoader.getConfiguredStage(shapeToEdit.get().getName(), "edit-shape-view.fxml").showAndWait();
            canvasManager.clearCanvas();
            canvasManager.redrawShapes();
            SelectedShape.getInstance().reset();
        }
        ActionTypeProvider.setType(ActionType.EMPTY);
    }

    /**
     * Attempts to save a shape based on the user's click point. If a shape is selected, it is saved as SVG;
     * otherwise, relevant messages are displayed to inform the user of the save status or errors.
     *
     * @param event The MouseEvent indicating where the user clicked.
     */
    private void attemptSaveShape(MouseEvent event) {
        var shapeToSave = canvasManager.findFirstShapeAtClickPoint(event);
        if (shapeToSave.isEmpty()) {
            setInformationText(AppMessages.NO_SHAPE_SELECTED_MSG);
        } else {
            ShapeSaver saver = new ShapeSaver();
            try {
                boolean success = saver.saveShapeAsSVG(shapeToSave.get());
                if (success) {
                    setInformationText(AppMessages.SVG_SAVE_SUCCESS_MSG);
                } else {
                    setInformationText(AppMessages.SVG_SAVE_CANCELLED_MSG);
                }
            } catch (ShapeSaveException shapeSaveException) {
                showAlert(AppMessages.SVG_SAVE_ERROR_MSG, shapeSaveException.getMessage());
            }
        }
        ActionTypeProvider.setType(ActionType.EMPTY);
    }

    /**
     * Displays an error alert to the user with the specified title and message.
     *
     * @param title   The title of the alert dialog.
     * @param message The main content message of the alert dialog.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private void attemptRotateShape(MouseEvent event) {
        // TODO: Add logic to save shapes on the canvas
    }

    /**
     * Attempts to draw the currently selected shape onto the canvas based on the user's click position.
     * If no shape is selected, a default instruction message is displayed. In the event of a runtime
     * exception during the drawing process, an error message is displayed and the exception is printed.
     *
     * @param event The MouseEvent indicating where the user clicked on the canvas.
     */
    private void attemptDrawShape(MouseEvent event) {
        var shapeToDraw = SelectedShape.getInstance().getSelectedShape();

        if (shapeToDraw == null)
            setInformationText(AppMessages.DEFAULT_INSTRUCTION_MSG);
        else {
            try {
                canvasManager.performDraw(event.getX(), event.getY(), shapeToDraw);
                setInformationText(AppMessages.BEAUTIFUL);
                SelectedShape.getInstance().reset();
            } catch (RuntimeException runtimeException) {
                setInformationText(AppMessages.DEFAULT_INSTRUCTION_MSG);
                runtimeException.printStackTrace();
            }
        }
    }


    /**
     * Attempts to remove the shape at the mouse event position.
     *
     * @param event The MouseEvent representing the canvas click event.
     */
    private void attemptRemoveShape(MouseEvent event) {
        var optionalShape = canvasManager.findFirstShapeAtClickPoint(event);

        if (optionalShape.isPresent()) {
            removeShapeAndReDrawCanvas(optionalShape.get());
        } else {
            setInformationText(AppMessages.NO_SHAPE_SELECTED_MSG);
        }
    ActionTypeProvider.setType(ActionType.EMPTY);
    }

    // Drawing and Canvas Manipulation Methods

    /**
     * Removes the specified shape template from the drawn shapes menu and updates the canvas.
     *
     * @param shape The shape template to remove.
     */
    private void removeShapeAndReDrawCanvas(ShapeTemplate shape) {
        DrawnShapeStorage.getInstance().removeShape(shape);
        canvasManager.clearCanvas();
        canvasManager.redrawShapes();
        setInformationText(AppMessages.SHAPE_REMOVAL_MSG + shape.getName());
    }

    // Utility and Miscellaneous Methods

    /**
     * Sets the text of the information text label.
     *
     * @param message The text message to set as information text
     */
    private void setInformationText(String message) {
        InformationTextProvider.setMessage(message);
    }
}
