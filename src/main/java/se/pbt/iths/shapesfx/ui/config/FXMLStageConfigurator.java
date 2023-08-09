package se.pbt.iths.shapesfx.ui.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.ShapesApplication;

import java.io.IOException;

/**
 * A utility class for loading and creating stages from FXML files.
 */
public class FXMLStageConfigurator {
    private final Stage stage;

    public FXMLStageConfigurator(Stage stage) {
        this.stage = stage;
    }

    /**
     * Loads the FXML file and returns the constructed stage.
     *
     * @return the constructed stage
     */
    public Stage getConfiguredStage(String title, String fxmlFile) {
            Parent root = loadFXMLContent(fxmlFile);
            configureModalStage(title, root);
            return this.stage;
    }

    /**
     * Load content from an FXML file.
     *
     * @return the parent node of the loaded FXML content
     */
    private Parent loadFXMLContent(String fxmlFile) {
        Parent parent;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ShapesApplication.class.getResource(fxmlFile));
            parent = fxmlLoader.load();

            return parent;
        } catch (IOException ioException) {
            throw new RuntimeException("Failed to load FXML file: " + fxmlFile, ioException);
        }
    }

    /**
     * Configures the stage to be an application modal window with the given title
     * and scene constructed from the provided parent node.
     *
     * @param title  the title to be set for the stage
     * @param parent the root node for the scene to be set on the stage
     */
    private void configureModalStage(String title, Parent parent) {
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(new Scene(parent));
    }
}
