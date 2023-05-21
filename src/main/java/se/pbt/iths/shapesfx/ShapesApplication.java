package se.pbt.iths.shapesfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.controller.ShapesController;
import se.pbt.iths.shapesfx.view.CanvasView;

import java.io.IOException;

public class ShapesApplication extends Application {
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShapesApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();

        ShapesController controller = fxmlLoader.getController();
        CanvasView canvasView = controller.getCanvasView();

        Scene scene = new Scene(root);

        // Bind the canvas size to the scene width and height
        canvasView.getCanvas().widthProperty().bind(scene.widthProperty());
        canvasView.getCanvas().heightProperty().bind(scene.heightProperty());

        stage.setTitle("Shapes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}