package se.pbt.iths.shapesfx;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.view.window.FXMLWindowLoader;

public class ShapesApplication extends Application {
    public void start(Stage stage) {
        FXMLWindowLoader shapesLoader = new FXMLWindowLoader(new Stage(), "Shapes", "main-view.fxml", Modality.APPLICATION_MODAL);
        shapesLoader.getWindowStage().showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}