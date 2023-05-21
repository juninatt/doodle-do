package se.pbt.iths.shapesfx.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import se.pbt.iths.shapesfx.view.CanvasView;

public class ShapesController {
    @FXML
    private BorderPane rootPane;
    @FXML
    public CanvasView canvasView;
    @FXML
    private Label welcomeText;

    public void initialize() {
        rootPane = new BorderPane();
        canvasView = new CanvasView();

        rootPane.setCenter(canvasView);
        VBox.setMargin(canvasView, new Insets(10, 10, 10, 10));
        welcomeText.setText("Welcome!");
    }

    @FXML
    private void handleCanvasClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        System.out.println("Mouse clicked at coordinates: (" + x + ", " + y + ")");
    }

    @FXML
    private void handleDrawCircle() {
        openShapeDialog("Circle");
    }

    @FXML
    private void handleDrawTriangle() {
        openShapeDialog("Triangle");
    }

    @FXML
    private void handleDrawSquare() {
        openShapeDialog("Square");
    }

    private void openShapeDialog(String shapeName) {
        // Open a small new window or perform any other action
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Draw Shape");
        alert.setHeaderText(null);
        alert.setContentText("You selected " + shapeName);
        alert.showAndWait();
    }
    public CanvasView getCanvasView() {
        return canvasView;
    }
}