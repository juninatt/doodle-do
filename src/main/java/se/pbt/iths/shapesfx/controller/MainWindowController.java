package se.pbt.iths.shapesfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.controller.manager.CanvasManager;
import se.pbt.iths.shapesfx.enums.ActionType;
import se.pbt.iths.shapesfx.models.ShapeTemplate;
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapeStorage;
import se.pbt.iths.shapesfx.modelsmanagement.SelectedShape;
import se.pbt.iths.shapesfx.utils.InformationTextProvider;
import se.pbt.iths.shapesfx.utils.ShapeMenuBarBinder;
import se.pbt.iths.shapesfx.view.canvas.CanvasView;
import se.pbt.iths.shapesfx.view.window.FXMLStageConfigurator;

import java.util.stream.IntStream;

// TODO: Fix case when selected shape is drawn again. Create new shape with new name?
// TODO: Improve exception handling
// TODO: Move bindings and actions creation to separate class
// TODO: Extract window creation and setup logic to separate class
// TODO: Extract canvas and graphic context logic to separate class
/**
 * The main controller class for the Shapes application.
 * Handles user interactions and shape creation/drawing.
 */
public class MainWindowController {

    private static final String WELCOME_MESSAGE = "Welcome!";
    private static final String BEAUTIFUL_MESSAGE = "Beautiful!";
    private static final String RESET_INFORMATION_TEXT = "Use the menu to create and draw a shape";
    private static final String REMOVED_SHAPE_MESSAGE = "Removed shape with name: ";
    private static final String NO_SHAPE_SELECTED_MESSAGE = "Did not get a shape there. Please try again!";
    private static final String EDIT_SHAPE_MESSAGE = "Edit shape: ";

    private static final String NO_SHAPE_SELECTED_REMOVE_MESSAGE = "No shape found at the clicked point. Choose 'remove' and try again to click on a shape to remove it!";

    private ActionType currentAction;
    CanvasManager canvasManager;

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
        this.currentAction = ActionType.EMPTY;
    }


    /**
     * Initializes the ShapesController with its default values and required instances.
     * Binds the menu items and information text property and sets action events for items.
     */
    public void initialize() {
        VBox.setMargin(canvasView, new Insets(10, 10, 10, 10));
        setUpMenuBar();
        setUpInformationLabel();

        canvasManager = new CanvasManager(canvasView);
    }

    // Private Helper Methods to initialize components

    /**
     * Initializes the menu bar by setting up item actions and binding the menu to saved shapes.
     */
    private void setUpMenuBar() {
        setUpSelectMenu();

        drawNewShapeMenu.getItems().forEach(menuItem -> menuItem.setOnAction(event -> {
            MenuItem sourceItem = (MenuItem) event.getSource();
            openNewWindow(sourceItem.getText(), "create-shape-view.fxml");
            currentAction = ActionType.DRAW;
        }));

        new ShapeMenuBarBinder(drawnShapesMenu, DrawnShapeStorage.getInstance()).bindMenuItems();

    }

    /**
     * Initializes the select menu by associating each menu item with its corresponding action.
     * Iterates over each menu item in the select menu and sets the action event handler.
     */
    private void setUpSelectMenu() {
        IntStream.range(0, selectMenu.getItems().size())
                .forEach(index -> {
                    MenuItem menuItem = selectMenu.getItems().get(index);
                    menuItem.setOnAction(this::setAction);
                });
    }


    /**
     * Updates the current action based on the selected menu item's text.
     * The text is converted to its corresponding {@link ActionType} enumeration.
     *
     * @param event The action event from the clicked menu item.
     */
    private void setAction(ActionEvent event) {
        var menuItem = (MenuItem) event.getSource();
        var text = menuItem.getText().toUpperCase();
        try {
            currentAction = ActionType.valueOf(text);
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
            currentAction = ActionType.EMPTY;
        }
    }


    /**
     * Set a welcome message to the label and bind it to {@link InformationTextProvider}.
     */
    private void setUpInformationLabel() {
        informationText.textProperty().bind(InformationTextProvider.getInformationTextProperty());
        setInformationText(WELCOME_MESSAGE);
    }

    // Event handling methods

    /**
     * Handles the click event on the canvas, performing actions based on the currentAction type.
     *
     * @param event The MouseEvent representing the canvas click event.
     */
    @FXML
    private void handleCanvasClick(MouseEvent event) {
        switch (currentAction) {
            case DRAW -> attemptDrawShape(event);
            case EDIT -> attemptEditShape(event);
            case SAVE -> attemptSaveShape(event);
            case ROTATE -> attemptRotateShape(event);
            case REMOVE -> attemptRemoveShape(event);
            case EMPTY -> setInformationText(RESET_INFORMATION_TEXT);
        }
    }

    // Shape handling methods

    private void attemptEditShape(MouseEvent event) {
        var shapeToEdit = canvasManager.findFirstShapeAtClickPoint(event);


        if (shapeToEdit.isEmpty())
            setInformationText(NO_SHAPE_SELECTED_MESSAGE);
        else {
            SelectedShape.getInstance().setSelectedShape(shapeToEdit.get());
            openNewWindow(EDIT_SHAPE_MESSAGE + shapeToEdit.get().getName(), "edit-shape-view.fxml");
            canvasManager.clearCanvas();
            canvasManager.redrawShapes();
            SelectedShape.getInstance().reset();
        }
        currentAction = ActionType.EMPTY;
    }

    private void attemptSaveShape(MouseEvent event) {
        // TODO: Add logic to save shapes on the canvas
    }

    private void attemptRotateShape(MouseEvent event) {
        // TODO: Add logic to save shapes on the canvas
    }

    /**
     * Attempts to draw the selected shape on the canvas at the mouse event position.
     * Handles successful drawing or provides feedback if no shape is selected.
     *
     * @param event The MouseEvent representing the canvas click event.
     */
    private void attemptDrawShape(MouseEvent event) {
        var shapeToDraw = SelectedShape.getInstance().getSelectedShape();

        if (shapeToDraw == null)
            setInformationText(RESET_INFORMATION_TEXT);
        else {
            try {
                canvasManager.performDraw(event.getX(), event.getY(), shapeToDraw);
                setInformationText(BEAUTIFUL_MESSAGE);
                SelectedShape.getInstance().reset();
            } catch (RuntimeException runtimeException) {
                setInformationText(RESET_INFORMATION_TEXT);
                runtimeException.printStackTrace();
            }
        }
    }


    /**
     * Attempts to remove the shape at the mouse event position.
     * Removes the shape if found, or provides feedback if not found.
     *
     * @param event The MouseEvent representing the canvas click event.
     */
    private void attemptRemoveShape(MouseEvent event) {
        var optionalShape = canvasManager.findFirstShapeAtClickPoint(event);

        if (optionalShape.isPresent()) {
            removeShapeAndReDrawCanvas(optionalShape.get());
        } else {
            setInformationText(NO_SHAPE_SELECTED_REMOVE_MESSAGE);
        }
        currentAction = ActionType.EMPTY;
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
        setInformationText(REMOVED_SHAPE_MESSAGE + shape.getName());
    }

    // Utility and Miscellaneous Methods

    /**
     * Resets the information text to its default value.
     */
    private void setInformationText(String message) {
        InformationTextProvider.getInformationTextProperty().set(message);
    }

    /**
     * Opens the shape creation window with the specified title. The shape creation window allows the user to set the size and color of the shape
     * before confirming and closing the window.
     * The window is displayed 'modally', meaning it blocks interaction with other windows until it is closed.
     *
     * @param title The title of the shape creation window.
     */
    private void openNewWindow(String title, String fxmlFile) {
        FXMLStageConfigurator windowLoader = new FXMLStageConfigurator(new Stage());
        windowLoader.getConfiguredStage(title, fxmlFile).showAndWait();
    }
}
