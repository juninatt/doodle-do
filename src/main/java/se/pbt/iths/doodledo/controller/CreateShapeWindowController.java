package se.pbt.iths.doodledo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import se.pbt.iths.doodledo.factory.ShapeFactory;
import se.pbt.iths.doodledo.modelsmanagement.SelectedShape;
import se.pbt.iths.doodledo.ui.resources.AppMessages;
import se.pbt.iths.doodledo.ui.utils.InformationTextProvider;

/**
 * Controller class for the shape creation dialog window.
 * Handles user interactions and shape creation based on user input.
 */
public class CreateShapeWindowController {

    // Non-final private instance fields
    private double size;
    private Paint color;

    // @FXML generated fields
    @FXML
    private TextField shapeNameField;
    @FXML
    private Slider sizeSlider;
    @FXML
    private ColorPicker colorPicker;


    /**
     * Initializes the controller by setting up listeners on sizeSlider and colorPicker,
     * and initializes the size and paint properties with their default values.
     */
    @FXML
    private void initialize() {
        size = sizeSlider.getValue();
        color = colorPicker.getValue();

        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> size = newValue.doubleValue());
        colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> color = newValue);
        InformationTextProvider.getMessage().set(AppMessages.INITIALIZE_SHAPE_MESSAGE);
    }

    /**
     * Handles the confirm shape button click event.
     * Creates the selected shape based on the dialog title, sets it as the selected shape in {@link SelectedShape},
     * and closes the stage if the creation was successful.
     */
    @FXML
    private void confirmShapeButtonClicked() {
        var stage = (Stage) sizeSlider.getScene().getWindow();
        String name = shapeNameField.getText();

        if (!allFieldsHaveValues()) {
            InformationTextProvider.getMessage().set(AppMessages.SHAPE_PROPERTIES_REQUIRED);
        }
        else {
            createShapeAndMakeSelected(stage.getTitle(), name);
            InformationTextProvider.getMessage().set(AppMessages.SHAPE_CREATION_SUCCESS);
            stage.close();
        }
    }

    /**
     * Creates a new shape and sets it as the {@link SelectedShape}.
     * @param shapeType the type of shape to create
     * @param name the name for the new shape
     */
    private void createShapeAndMakeSelected(String shapeType, String name) {
        var newShape = ShapeFactory.createShape(shapeType, name, color, size);
        SelectedShape.getInstance().setSelectedShape(newShape);
    }


    /**
     * Check if all the required fields have valid values
     * @return boolean indicating whether all fields are valid
     */
    private boolean allFieldsHaveValues() {
        if (shapeNameField == null || color == null)
            return false;

        return shapeNameField.getText().length() > 0
                && size > 0.0;
    }
}
