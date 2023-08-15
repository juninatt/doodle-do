package se.pbt.iths.shapesfx;

import javafx.application.Application;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.ui.config.FXMLStageConfigurator;

// TODO: Add choice of angle for user when rotating shape
// TODO: Created shape is added to drawn shapes menu before drawn
// TODO: Create sub classes for shapes with vertices
// TODO: Fix case when selected shape is drawn again. Create new shape with new name?
// TODO: Improve exception handling with alert message
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