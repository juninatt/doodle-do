package se.pbt.iths.shapesfx.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.model.shapes.MyCircle;
import se.pbt.iths.shapesfx.model.shapes.MySquare;
import se.pbt.iths.shapesfx.model.shapes.MyTriangle;
import se.pbt.iths.shapesfx.modelmanagement.SavedShapes;
import se.pbt.iths.shapesfx.modelmanagement.SelectedShape;
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
    public Menu savedShapesMenu;
    @FXML
    private Label informationText;



    /**
     * Initializes the ShapesController.
     * Sets the margin for the canvasView and displays a welcome message in the informationLabel.
     */
    public void initialize() {
        VBox.setMargin(canvasView, new Insets(10, 10, 10, 10));
        informationText.setText("Welcome!");
    }

    private void handleDrawSavedShape(Shape shape) {

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
        System.out.println(x + "      " + y);
        try {
            var shape = SelectedShape.getInstance().getSelectedShape();
            switch (shape.getClass().getSimpleName()) {
                case "MyCircle" -> drawCircle(shape, x, y);
                case "MySquare" -> drawSquare(shape, x, y);
                case "MyTriangle" -> drawTriangle((MyTriangle) shape, x, y);
                default -> informationText.setText("Current shape is not recognized. If problem persists contact support");
            }
            populateSavedShapesMenu();
        } catch (RuntimeException runtimeException) {
            informationText.setText("Use the menu to create and draw a shape");
            System.out.println("Could not draw shape on canvas. " + runtimeException.getMessage());
        }
    }
    private void populateSavedShapesMenu() {
        ObservableList<Shape> savedShapes = SavedShapes.getInstance().getSavedShapes();
        savedShapesMenu.getItems().clear();

        if (savedShapes.isEmpty()) {
            savedShapesMenu.getItems().add(new MenuItem("Empty"));
        } else {
            for (Shape shape : savedShapes) {
                MenuItem menuItem = new MenuItem(shape.getClass().getSimpleName());
                menuItem.setOnAction(e -> handleDrawSavedShape(shape));
                savedShapesMenu.getItems().add(menuItem);
            }
        }
    }

    /**
     * Draws a circle on the x and y coordinates of the canvas and then resets the current {@link SelectedShape}
     */
    private void drawCircle(Shape circle, double x, double y) {
        canvasView.drawCircle((MyCircle) circle, x, y);
        SelectedShape.getInstance().reset();
    }

    /**
     * Draws a square on the x and y coordinates of the canvas and then resets the current {@link SelectedShape}
     */
    private void drawSquare(Shape square, double x, double y) {
        canvasView.drawSquare((MySquare) square, x, y);
        SelectedShape.getInstance().reset();
    }

    /**
     * Draws a triangle on the x and y coordinates of the canvas and then resets the current {@link SelectedShape}
     */
    private void drawTriangle(MyTriangle triangle, double x, double y) {
        // Store triangle side length divided by two to configure triangle center on canvas
        var distanceFromCenter = triangle.getSize() / 2;

        // Create two arrays holding the x- and y-coordinates of the triangle, based on the coordinates of the mouse click on the canvas
        var xCoordinates = new double[]{x, x - distanceFromCenter, x + distanceFromCenter};
        var yCoordinates = new double[]{y - distanceFromCenter, y + distanceFromCenter, y + distanceFromCenter};

        // Add points to triangle
        triangle.getPoints().setAll(xCoordinates[0], yCoordinates[0], xCoordinates[1], yCoordinates[1], xCoordinates[2], yCoordinates[2]);

        canvasView.drawTriangle(triangle, xCoordinates, yCoordinates);
        SelectedShape.getInstance().reset();
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
                informationText.setText("Set the size and color of your shape and press 'Confirm'.\n Then click on the canvas to draw your masterpiece!");
    }
}