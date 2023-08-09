package se.pbt.iths.shapesfx;

import javafx.application.Application;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.ui.config.FXMLStageConfigurator;

public class ShapesApplication extends Application {
    public void start(Stage stage) {
        FXMLStageConfigurator shapesLoaderFXML = new FXMLStageConfigurator(new Stage());
        shapesLoaderFXML.getConfiguredStage("Shapes", "main-view.fxml")
                .showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}