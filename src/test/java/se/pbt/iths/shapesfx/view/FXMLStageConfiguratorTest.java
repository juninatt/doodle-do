package se.pbt.iths.shapesfx.view;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.pbt.iths.shapesfx.view.window.FXMLStageConfigurator;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FXMLStageConfiguratorTest {

    Stage mockStage = mock(Stage.class);
    FXMLLoader mockLoader = mock(FXMLLoader.class);

    @Test
    @DisplayName("RuntimeException should be thrown when no fxml file is found")
    void getConfiguredStage_throwsRuntimeException_WhenNoFxmlFileIsFound() throws Exception {
        when(mockLoader.load()).thenThrow(IOException.class);
        FXMLStageConfigurator configurator = new FXMLStageConfigurator(mockStage);

        assertThrows(RuntimeException.class, () -> configurator.getConfiguredStage("Test Window", "non-existent-file.fxml"));
    }
}
