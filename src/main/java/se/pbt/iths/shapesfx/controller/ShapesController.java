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
import se.pbt.iths.shapesfx.model.MyCircle;
import se.pbt.iths.shapesfx.model.MySquare;
import se.pbt.iths.shapesfx.model.MyTriangle;
import se.pbt.iths.shapesfx.modelmanagement.SavedShapes;
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
        if (canvasView == null) {
            canvasView = new CanvasView();
            rootPane.setCenter(canvasView);
            VBox.setMargin(canvasView, new Insets(10, 10, 10, 10));
        }
        welcomeText.setText("Welcome!");
    }

    @FXML
    private void handleDrawCircle() {
        openShapeCreationWindow("create-shape-view.fxml", "Create Circle");
    }

    @FXML
    private void handleDrawTriangle() {
        openShapeCreationWindow("create-shape-view.fxml", "Create Triangle");
    }

    @FXML
    private void handleDrawSquare() {
        openShapeCreationWindow("create-shape-view.fxml", "Create Square");
    }

    @FXML
    private void handleCanvasClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        System.out.println("Mouse clicked at coordinates: (" + x + ", " + y + ")");

        for (MyCircle circle : SavedShapes.getInstance().getSavedCircles()) {
            if (circle.isSelected()) {
                canvasView.drawCircle(circle, event.getX(), event.getY());
                circle.setSelected(false);
            }
        }

        for (MySquare square : SavedShapes.getInstance().getSavedSquares()) {
            if (square.isSelected()) {
                canvasView.drawSquare(square, x, y);
                square.setSelected(false);
            }
        }

        for (MyTriangle triangle : SavedShapes.getInstance().getSavedTriangles()) {
            if (triangle.isSelected()) {
                double size = triangle.getSize();
                double halfSize = size / 2;

                double[] xCoordinates = new double[]{x, x - halfSize, x + halfSize};
                double[] yCoordinates = new double[]{y - halfSize, y + halfSize, y + halfSize};

                triangle.getPoints().setAll(xCoordinates[0], yCoordinates[0], xCoordinates[1], yCoordinates[1], xCoordinates[2], yCoordinates[2]);

                canvasView.drawTriangle(triangle, xCoordinates, yCoordinates);

                triangle.setSelected(false);
            }
        }
    }


        private void openShapeCreationWindow (String fxmlFile, String title){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ShapesApplication.class.getResource(fxmlFile));
                Parent root = fxmlLoader.load();
                CreateShapeController controller = fxmlLoader.getController();

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
}