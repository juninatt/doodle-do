package se.pbt.iths.shapesfx.view;

import javafx.stage.Modality;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.pbt.iths.shapesfx.view.window.FXMLWindowLoader;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class FXMLWindowLoaderTest {

    Stage mockStage = mock(Stage.class);

    @Test
    @DisplayName("IllegalStateException should be thrown when no fxml file is found")
    void loadWindow_throwsIllegalStateException_WhenNoFxmlFileIsFound() {
        FXMLWindowLoader windowLoader = new FXMLWindowLoader(mockStage, "Test Window", "non existent file", Modality.NONE);
        assertThrows(IllegalStateException.class, windowLoader::getWindowStage);
    }

    @Test
    @DisplayName("The class constructor should assign instance variables correctly")
    void classConstructor_AssignsFieldsCorrectly() {
        FXMLWindowLoader windowLoader = new FXMLWindowLoader(mockStage, "Test Window", "fxml/test-view.fxml", Modality.APPLICATION_MODAL);
        Stage stage = windowLoader.stage();
        assertNotNull(stage);
        assertEquals("Test Window", windowLoader.title());
        assertEquals("fxml/test-view.fxml", windowLoader.fxmlFile());
        assertEquals(Modality.APPLICATION_MODAL, windowLoader.modality());
    }
}
