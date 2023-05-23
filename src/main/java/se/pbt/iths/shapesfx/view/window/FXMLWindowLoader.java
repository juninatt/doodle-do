package se.pbt.iths.shapesfx.view.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.ShapesApplication;

import java.io.IOException;

public class FXMLWindowLoader {
    private final String title;
    private final String fxmlFile;
    private final Modality modality;

    public FXMLWindowLoader(String title, String fxmlFile, Modality modality) {
        this.title = title;
        this.fxmlFile = fxmlFile;
        this.modality = modality;
    }

    public void loadWindow(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ShapesApplication.class.getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            createModalStage(root);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void createModalStage(Parent parent) {
        Stage stage = new Stage();
        stage.initModality(modality);
        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
