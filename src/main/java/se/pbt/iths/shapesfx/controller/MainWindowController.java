package se.pbt.iths.shapesfx.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.interfaces.Drawable;
import se.pbt.iths.shapesfx.modelsmanagement.SelectedShape;
import se.pbt.iths.shapesfx.utils.InformationTextProvider;
import se.pbt.iths.shapesfx.utils.MenuActionUtils;
import se.pbt.iths.shapesfx.utils.MenuBarBinder;
import se.pbt.iths.shapesfx.view.canvas.CanvasView;
import se.pbt.iths.shapesfx.view.window.FXMLWindowLoader;

// TODO: Improve exception handling
/**
 * The main controller class for the Shapes application.
 * Handles user interactions and shape creation/drawing.
 */
public class MainWindowController {

    private static final String WELCOME_MESSAGE = "Welcome!";
    private static final String BEAUTIFUL_MESSAGE = "Beautiful!";
    private static final String NO_SHAPE_SELECTED_MESSAGE = "Draw new shape or select old one to add it to the canvas.";
    private static final String RESET_INFORMATION_TEXT = "Use the menu to create and draw a shape";


    @FXML
    private CanvasView canvasView;
    @FXML
    private Menu drawNewShapeMenu;
    @FXML
    private Menu drawnShapesMenu;
    @FXML
    private Menu optionsMenu;
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
    }

    /**
     * Handles the click event on the canvas.
     * Retrieves the selected shape from the SelectedShape manager and draws it on the canvas
     * at the clicked position (x, y).
     *
     * @param event The MouseEvent representing the canvas click event.
     */
    @FXML
    private void handleCanvasClick(MouseEvent event) {
        Drawable shapeToDraw = SelectedShape.getInstance().getSelectedShape();
        if (shapeToDraw == null)
            handleNoSelectedShape();
        else {
            drawShape(event, shapeToDraw);
        }
    }

    /**
     * Attempts to draw the provided shape at the location of the MouseEvent on the canvas.
     * If the shape is drawn successfully, the shape is added to the DrawnShapesMenu,
     * a success message is displayed, and the current selection is reset.
     *
     * @param event MouseEvent representing the canvas click, provides the coordinates for where the shape should be drawn.
     * @param shapeToDraw The Drawable shape to be drawn on the canvas.
     */
    private void drawShape(MouseEvent event, Drawable shapeToDraw) {
        try {
            performDraw(event, shapeToDraw);
            handleSuccessfulDraw();
        } catch (RuntimeException runtimeException) {
            handleException(runtimeException);
        }
    }

    /**
     * Handles the necessary actions after a successful draw operation.
     * Displays a success message and resets the currently selected shape.
     */
    private static void handleSuccessfulDraw() {
        InformationTextProvider.getInformationTextProperty().set(BEAUTIFUL_MESSAGE);
        SelectedShape.getInstance().reset();
    }

    /**
     * Handles the exception when an error occurs during shape drawing.
     * Resets the information text and prints the stack trace of the exception.
     *
     * @param exception The exception to handle.
     */
    private void handleException(Exception exception) {
        resetInformationText();
        exception.printStackTrace();
    }

    /**
     * Draws the provided shape at the location of the mouse event on the canvas.
     *
     * @param event The MouseEvent representing the location on the canvas where the user clicked.
     * @param shape The Drawable shape to be drawn on the canvas.
     */
    private void performDraw(MouseEvent event, Drawable shape) {
        double x = event.getX();
        double y = event.getY();
        shape.draw(canvasView.getCanvasNode().getGraphicsContext2D(), x, y);
    }

    /**
     * Handles the event when no shape is selected.
     */
    private void handleNoSelectedShape() {
        InformationTextProvider.getInformationTextProperty().set(NO_SHAPE_SELECTED_MESSAGE);
    }

    /**
     * Resets the information text to its default value.
     */
    private void resetInformationText() {
        InformationTextProvider.getInformationTextProperty().set(RESET_INFORMATION_TEXT);
    }

    /**
     * Set up the menu bar with custom menu items actions in {@link MenuActionUtils}
     * and bind menu to saved shapes in {@link MenuBarBinder}.
     */
    private void setUpMenuBar() {

        EventHandler<ActionEvent> shapeMenuAction = MenuActionUtils.createShapeMenuAction(this::openShapeCreationWindow);

        drawNewShapeMenu.getItems().forEach(menuItem -> menuItem.setOnAction(shapeMenuAction));

        new MenuBarBinder(drawnShapesMenu).bindMenuItems();
    }

    /**
     * Set a welcome message to the label and bind it to {@link InformationTextProvider}.
     */
    private void setUpInformationLabel() {
        informationText.textProperty().bind(InformationTextProvider.getInformationTextProperty());
        InformationTextProvider.getInformationTextProperty().set(WELCOME_MESSAGE);
    }

    // TODO: Extract logic to separate class
    /**
     * Opens the shape creation window with the specified title. The shape creation window allows the user to set the size and color of the shape
     * before confirming and closing the window.
     * The window is displayed 'modally', meaning it blocks interaction with other windows until it is closed.
     *
     * @param title The title of the shape creation window.
     */
    private void openShapeCreationWindow (String title){
        FXMLWindowLoader windowLoader = new FXMLWindowLoader(new Stage(), title, "create-shape-view.fxml", Modality.APPLICATION_MODAL);
        windowLoader.loadWindow();
    }



    public void handleOptionsEdit() {
    }

    public void handleOptionSave() {
    }

    public void handleOptionLoad() {
    }
}