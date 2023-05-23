package se.pbt.iths.shapesfx.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.model.MyCircle;
import se.pbt.iths.shapesfx.model.MySquare;
import se.pbt.iths.shapesfx.model.MyTriangle;
import se.pbt.iths.shapesfx.modelmanagement.SelectedShape;
import se.pbt.iths.shapesfx.view.canvas.CanvasView;
import se.pbt.iths.shapesfx.view.window.FXMLWindowLoader;

public class ShapesController {

    private static final String CREATE_CIRCLE_TITLE = "Create Circle";
    private static final String CREATE_TRIANGLE_TITLE = "Create Triangle";
    private static final String CREATE_SQUARE_TITLE = "Create Square";
    @FXML
    public CanvasView canvasView;
    @FXML
    private Label informationLabel;

    public void initialize() {
        VBox.setMargin(canvasView, new Insets(10, 10, 10, 10));
        informationLabel.setText("Welcome!");
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

    @FXML
    private void handleCanvasClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        try {
            var shape = SelectedShape.getInstance().getSelectedShape();
            switch (shape.getClass().getSimpleName()) {
                case "MyCircle" -> drawCircle(shape, x, y);
                case "MySquare" -> drawSquare(shape, x, y);
                case "MyTriangle" -> drawTriangle((MyTriangle) shape, x, y);
                default -> informationLabel.setText("Current shape is not recognized. If problem persists contact support");
            }
        } catch (NullPointerException nullPointerException) {
            informationLabel.setText("Use the menu to create and draw a shape");
        }
    }

    private void drawCircle(Shape circle, double x, double y) {
        canvasView.drawCircle((MyCircle) circle, x, y);
        SelectedShape.getInstance().reset();
    }

    private void drawSquare(Shape square, double x, double y) {
        canvasView.drawSquare((MySquare) square, x, y);
        SelectedShape.getInstance().reset();
    }

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


    private void openShapeCreationWindow (String title){
                FXMLWindowLoader windowLoader = new FXMLWindowLoader(new Stage(), title, "create-shape-view.fxml", Modality.APPLICATION_MODAL);
                windowLoader.loadWindow();
    }
}