package se.pbt.iths.shapesfx.view.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.ShapesApplication;

import java.io.IOException;

public record FXMLWindowLoader(Stage stage, String title, String fxmlFile, Modality modality) {

    public void loadWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ShapesApplication.class.getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            createModalStage(root);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void createModalStage(Parent parent) {
        stage.initModality(modality);
        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
