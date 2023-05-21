package se.pbt.iths.shapesfx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.ShapesApplication;
import se.pbt.iths.shapesfx.view.CanvasView;

import java.io.IOException;

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
        openShapeCreationWindow("create-circle.fxml", "Create Circle");
    }

    @FXML
    private void handleDrawTriangle() {
        openShapeCreationWindow("create-triangle.fxml", "Create Triangle");
    }

    @FXML
    private void handleDrawSquare() {
        openShapeCreationWindow("create-square.fxml", "Create Square");
    }


    private void openShapeCreationWindow(String fxmlFile, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ShapesApplication.class.getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            ShapeCreationController controller = fxmlLoader.getController();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();

            controller.setStage(stage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public CanvasView getCanvasView() {
        return canvasView;
    }
}