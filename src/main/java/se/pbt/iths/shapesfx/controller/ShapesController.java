package se.pbt.iths.shapesfx.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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
    private Label welcomeText;

    public void initialize() {
        VBox.setMargin(canvasView, new Insets(10, 10, 10, 10));
        welcomeText.setText("Welcome!");
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
        var shape = SelectedShape.getInstance().getSelectedShape();
        if (shape instanceof MyCircle circle)
            canvasView.drawCircle(circle, event.getX(), event.getY());
        else if (shape instanceof MySquare square)
            canvasView.drawSquare(square, x, y);
        else if (shape instanceof MyTriangle triangle) {
            var size = triangle.getSize();
            var halfSize = size / 2;

            var xCoordinates = new double[]{x, x - halfSize, x + halfSize};
            var yCoordinates = new double[]{y - halfSize, y + halfSize, y + halfSize};

            triangle.getPoints().setAll(xCoordinates[0], yCoordinates[0], xCoordinates[1], yCoordinates[1], xCoordinates[2], yCoordinates[2]);

            canvasView.drawTriangle(triangle, xCoordinates, yCoordinates);
        }
    }


        private void openShapeCreationWindow (String title){
                FXMLWindowLoader windowLoader = new FXMLWindowLoader(title, "create-shape-view.fxml", Modality.APPLICATION_MODAL);
                windowLoader.loadWindow();
    }
}