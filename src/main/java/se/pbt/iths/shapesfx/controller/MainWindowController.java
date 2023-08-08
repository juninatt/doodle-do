package se.pbt.iths.shapesfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.enums.ActionType;
import se.pbt.iths.shapesfx.models.ShapeTemplate;
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapeStorage;
import se.pbt.iths.shapesfx.modelsmanagement.SelectedShape;
import se.pbt.iths.shapesfx.utils.InformationTextProvider;
import se.pbt.iths.shapesfx.utils.MenuBarBinder;
import se.pbt.iths.shapesfx.view.canvas.CanvasView;
import se.pbt.iths.shapesfx.view.window.FXMLWindowLoader;

import java.util.Optional;
import java.util.stream.IntStream;

// TODO: Fix case when selected shape is drawn again. Create new shape with new name?
// TODO: Improve exception handling
// TODO: Replace Strings with constants
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


    /**
     * Initializes the ShapesController.
     * Sets the margin for the canvasView and displays a welcome message in the informationLabel.
     * Instantiates a new MenuBarBinder object and binds the menu items from the drawnShapesMenu.
     * Binds the informationText text property to the text property provided by InformationTextProvider.
     */
    public void initialize() {
        VBox.setMargin(canvasView, new Insets(10, 10, 10, 10));
        setUpMenuBar();
        setUpInformationLabel();
        currentAction = ActionType.EMPTY;
    }

    // Private Helper Methods to initialize components

    /**
     * Initializes the menu bar by setting up item actions and binding the menu to saved shapes.
     */
    private void setUpMenuBar() {
        setUpSelectMenu();

        drawNewShapeMenu.getItems().forEach(menuItem -> menuItem.setOnAction(event -> {
            MenuItem sourceItem = (MenuItem) event.getSource();
            openShapeCreationWindow(sourceItem.getText());
        }));

        new MenuBarBinder(drawnShapesMenu).bindMenuItems();
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
     * Depending on the current action, this method may delegate to methods responsible for drawing,
     * editing, saving, rotating, or removing a shape at the clicked position (x, y).
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
        var shapeToEdit = findFirstShapeAtClickPoint(event);


        if (shapeToEdit.isEmpty())
            setInformationText(NO_SHAPE_SELECTED_MESSAGE);
        else {
            SelectedShape.getInstance().setSelectedShape(shapeToEdit.get());
            openEditShapeWindow(EDIT_SHAPE_MESSAGE + shapeToEdit.get().getName());
            clearCanvas();
            redrawShapes();
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
                performDraw(event.getX(), event.getY(), shapeToDraw);
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
        var optionalShape = findFirstShapeAtClickPoint(event);

        if (optionalShape.isPresent()) {
            removeShape(optionalShape.get());
        } else {
            setInformationText(NO_SHAPE_SELECTED_REMOVE_MESSAGE);
        }
        currentAction = ActionType.EMPTY;
    }

    // Drawing and Canvas Manipulation Methods

    /**
     * Removes the specified shape template from the drawn shapes menu and updates the canvas.
     *
     * @param shapeTemplate The shape template to remove.
     */
    private void removeShape(ShapeTemplate shapeTemplate) {
        DrawnShapeStorage.getInstance().removeShape(shapeTemplate);
        clearCanvas();
        redrawShapes();
        setInformationText(REMOVED_SHAPE_MESSAGE + shapeTemplate.getName());
    }

    /**
     * Finds the first shape template at the clicked point on the canvas.
     *
     * @param event The mouse event containing the clicked point's coordinates.
     * @return An optional containing the found shape template, or an empty optional if no shape was found.
     */
    private Optional<ShapeTemplate> findFirstShapeAtClickPoint(MouseEvent event) {
        return DrawnShapeStorage.getInstance().getDrawnShapes().stream()
                .filter(shape -> shape.contains(event.getX(), event.getY()))
                .findFirst();
    }

    /**
     * Redraws all the shapes saved in the DrawnShapesMenu on the canvas.
     */
    private void redrawShapes() {
        DrawnShapeStorage.getInstance().getDrawnShapes()
                .forEach(shapeTemplate -> performDraw(shapeTemplate.getCx(), shapeTemplate.getCy(), shapeTemplate));
    }


    /**
     * Draws the specified shape template at the given coordinates on the canvas.
     *
     * @param x     The x-coordinate where the shape will be drawn.
     * @param y     The y-coordinate where the shape will be drawn.
     * @param shape The shape template to draw.
     */
    private void performDraw(double x, double y, ShapeTemplate shape) {
        try {
            var gc = canvasView.getCanvasNode().getGraphicsContext2D();
            gc.setFill(shape.getFill());
            shape.draw(gc, x, y);
            shape.setCx(x);
            shape.setCy(y);
        } catch (RuntimeException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Clears the entire canvas, removing all drawn content.
     */
    private void clearCanvas() {
        GraphicsContext gc = canvasView.getCanvasNode().getGraphicsContext2D();
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
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
    private void openShapeCreationWindow(String title) {
        FXMLWindowLoader windowLoader = new FXMLWindowLoader(new Stage(), title, "create-shape-view.fxml", Modality.APPLICATION_MODAL);
        windowLoader.getWindowStage().showAndWait();
        currentAction = ActionType.DRAW;
    }

    private void openEditShapeWindow(String title) {
        FXMLWindowLoader windowLoader = new FXMLWindowLoader(new Stage(), title, "edit-shape-view.fxml", Modality.APPLICATION_MODAL);
        windowLoader.getWindowStage().showAndWait();
        currentAction = ActionType.EDIT;
    }
}
