package se.pbt.iths.shapesfx.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.exceptions.DrawingException;
import se.pbt.iths.shapesfx.model.shapes.MyCircle;
import se.pbt.iths.shapesfx.model.shapes.MySquare;
import se.pbt.iths.shapesfx.model.shapes.MyTriangle;
import se.pbt.iths.shapesfx.modelmanagement.SelectedShape;
import se.pbt.iths.shapesfx.utils.InformationTextProvider;
import se.pbt.iths.shapesfx.utils.MenuBarBinder;
import se.pbt.iths.shapesfx.view.canvas.CanvasView;
import se.pbt.iths.shapesfx.view.window.FXMLWindowLoader;

/**
 * The main controller class for the Shapes application.
 * Handles user interactions and shape creation/drawing.
 */
public class ShapesController {

    private static final String CREATE_CIRCLE_TITLE = "Create Circle";
    private static final String CREATE_TRIANGLE_TITLE = "Create Triangle";
    private static final String CREATE_SQUARE_TITLE = "Create Square";


    @FXML
    public CanvasView canvasView;
    @FXML
    public Menu drawnShapesMenu;
    @FXML
    private Label informationText;



    /**
     * Initializes the ShapesController.
     * Sets the margin for the canvasView and displays a welcome message in the informationLabel.
     */
    public void initialize() {
        VBox.setMargin(canvasView, new Insets(10, 10, 10, 10));

        new MenuBarBinder(drawnShapesMenu).bindMenuItems();

        informationText.textProperty().bind(InformationTextProvider.getInformationTextProperty());
        InformationTextProvider.getInformationTextProperty().set("Welcome!");

    }

    @FXML
    private void handleDrawCircle() {
        openShapeCreationWindow(CREATE_CIRCLE_TITLE);
    }

    @FXML
    private void handleDrawTriangle() {
        openShapeCreationWindow(CREATE_TRIANGLE_TITLE);
    }

    @FXML
    private void handleDrawSquare() {
        openShapeCreationWindow(CREATE_SQUARE_TITLE);
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
        double x = event.getX();
        double y = event.getY();
        var shape = SelectedShape.getInstance().getSelectedShape();
        if (shape == null)
            InformationTextProvider.getInformationTextProperty().set("Draw new shape or select old one to add it to the canvas.");
        else {
            try {
                switch (shape.getClass().getSimpleName()) {
                    case "MyCircle" -> drawCircle(shape, x, y);
                    case "MySquare" -> drawSquare(shape, x, y);
                    case "MyTriangle" -> drawTriangle((MyTriangle) shape, x, y);
                    default -> informationText.setText("Current shape is not recognized. If problem persists contact support");
                }
                InformationTextProvider.getInformationTextProperty().set("Beautiful!");
                SelectedShape.getInstance().reset();
            } catch (DrawingException drawingException) {
                InformationTextProvider.getInformationTextProperty().set("Application failed to draw your shape.");
                drawingException.printStackTrace();
                throw new RuntimeException("Failed to draw shape. " + drawingException.getMessage());
            } catch (RuntimeException runtimeException) {
                InformationTextProvider.getInformationTextProperty().set("Use the menu to create and draw a shape");
                System.out.println(runtimeException.getMessage());
            }
        }
    }


    /**
     * Draws a circle on the x and y coordinates of the canvas and then resets the current {@link SelectedShape}
     */
    private void drawCircle(Shape circle, double x, double y) throws DrawingException {
        canvasView.drawCircle((MyCircle) circle, x, y);
    }

    /**
     * Draws a square on the x and y coordinates of the canvas and then resets the current {@link SelectedShape}
     */
    private void drawSquare(Shape square, double x, double y) throws DrawingException{
            canvasView.drawSquare((MySquare) square, x, y);
    }

    /**
     * Draws a triangle on the x and y coordinates of the canvas and then resets the current {@link SelectedShape}
     */
    private void drawTriangle(MyTriangle triangle, double x, double y) throws DrawingException {
        // Store triangle side length divided by two to configure triangle center on canvas
        var distanceFromCenter = triangle.getSize() / 2;

        // Create two arrays holding the x- and y-coordinates of the triangle, based on the coordinates of the mouse click on the canvas
        var xCoordinates = new double[]{x, x - distanceFromCenter, x + distanceFromCenter};
        var yCoordinates = new double[]{y - distanceFromCenter, y + distanceFromCenter, y + distanceFromCenter};

        // Add points to triangle
        triangle.getPoints().setAll(xCoordinates[0], yCoordinates[0], xCoordinates[1], yCoordinates[1], xCoordinates[2], yCoordinates[2]);

        canvasView.drawTriangle(triangle, xCoordinates, yCoordinates);
    }

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