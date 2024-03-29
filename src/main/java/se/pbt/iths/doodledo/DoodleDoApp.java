package se.pbt.iths.doodledo;

import javafx.application.Application;
import javafx.stage.Stage;
import se.pbt.iths.doodledo.ui.config.FXMLStageConfigurator;

// TODO: Center the generation of information messages
// TODO: Add choice of angle for user when rotating shape
// TODO: Improve exception handling
public class DoodleDoApp extends Application {
    public void start(Stage stage) {
        FXMLStageConfigurator shapesLoaderFXML = new FXMLStageConfigurator(new Stage());
        shapesLoaderFXML.getConfiguredStage("Shapes", "main-view.fxml")
                .showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}