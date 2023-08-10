package se.pbt.iths.shapesfx.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.controller.manager.CanvasManager;
import se.pbt.iths.shapesfx.enums.ActionType;
import se.pbt.iths.shapesfx.models.ShapeTemplate;
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapeStorage;
import se.pbt.iths.shapesfx.modelsmanagement.SelectedShape;
import se.pbt.iths.shapesfx.ui.config.DrawShapeMenuConfigurator;
import se.pbt.iths.shapesfx.ui.config.AvailableShapesMenuConfigurator;
import se.pbt.iths.shapesfx.ui.config.FXMLStageConfigurator;
import se.pbt.iths.shapesfx.ui.config.SelectMenuConfigurator;
import se.pbt.iths.shapesfx.ui.resources.AppMessages;
import se.pbt.iths.shapesfx.ui.utils.ActionTypeProvider;
import se.pbt.iths.shapesfx.ui.utils.InformationTextProvider;
import se.pbt.iths.shapesfx.ui.views.canvas.CanvasView;

// TODO: Fix case when selected shape is drawn again. Create new shape with new name?
// TODO: Improve exception handling
// TODO: Move bindings and actions creation to separate class
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
            case EMPTY -> setInformationText(AppMessages.DEFAULT);
        }
    }

    // Shape handling methods

    /**
     * Edits the shape at the clicked canvas point. If a shape is found, it's set as the selected shape, the edit window is shown,
     * and the canvas is refreshed.
     *
     * @param event The canvas click MouseEvent.
     */
    private void attemptEditShape(MouseEvent event) {
        var shapeToEdit = canvasManager.findFirstShapeAtClickPoint(event);
        if (shapeToEdit.isEmpty())
            setInformationText(AppMessages.NO_SHAPE_SELECTED);
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

    private void attemptSaveShape(MouseEvent event) {
        // TODO: Add logic to save shapes on the canvas
    }

    private void attemptRotateShape(MouseEvent event) {
        // TODO: Add logic to save shapes on the canvas
    }

    /**
     * Attempts to draw the selected shape on the canvas at the mouse event position.
     *
     * @param event The MouseEvent representing the canvas click event.
     */
    private void attemptDrawShape(MouseEvent event) {
        var shapeToDraw = SelectedShape.getInstance().getSelectedShape();

        if (shapeToDraw == null)
            setInformationText(AppMessages.DEFAULT);
        else {
            try {
                canvasManager.performDraw(event.getX(), event.getY(), shapeToDraw);
                setInformationText(AppMessages.BEAUTIFUL);
                SelectedShape.getInstance().reset();
            } catch (RuntimeException runtimeException) {
                setInformationText(AppMessages.DEFAULT);
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
            setInformationText(AppMessages.NO_SHAPE_SELECTED);
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
        setInformationText(AppMessages.REMOVED_SHAPE_NAMED + shape.getName());
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
