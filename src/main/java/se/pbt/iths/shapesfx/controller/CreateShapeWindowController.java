package se.pbt.iths.shapesfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.factory.ShapeFactory;
import se.pbt.iths.shapesfx.models.ShapeTemplate;
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapeStorage;
import se.pbt.iths.shapesfx.modelsmanagement.SelectedShape;
import se.pbt.iths.shapesfx.utils.InformationTextProvider;

// TODO: Improve exception handling
/**
 * Controller class for the shape creation dialog window.
 * Handles user interactions and shape creation based on user input.
 */
public class CreateShapeWindowController {

    private static final String INITIALIZE_SHAPE_MESSAGE = "Set the size and color of your shape and press 'Confirm'.";
    private static final String SHAPE_PROPERTIES_REQUIRED = "All of the shape's properties must have a value";
    private static final String SHAPE_CREATION_SUCCESS = "Click on the canvas to add your shape.";
    private static final String SHAPE_NAME_TAKEN = "Sorry but that name is taken.";

    private double size;
    private Paint color;

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
        InformationTextProvider.getInformationTextProperty().set(INITIALIZE_SHAPE_MESSAGE);
    }

    /**
     * Handles the confirm shape button click event.
     * Creates the selected shape based on the dialog title, sets it as the selected shape in {@link SelectedShape}.
     */
    @FXML
    private void confirmShapeButtonClicked() {
        Stage stage = (Stage) sizeSlider.getScene().getWindow();
        if (!allFieldsHaveValues()) {
            InformationTextProvider.getInformationTextProperty().set(SHAPE_PROPERTIES_REQUIRED);
        } else {
            String name = shapeNameField.getText();
            ShapeTemplate newShape;

            var optionalDuplicateName = DrawnShapeStorage.getInstance().getByName(name);
            if (optionalDuplicateName.isEmpty()) {
                newShape = ShapeFactory.createShape(stage.getTitle(), name, color, size);
                DrawnShapeStorage.getInstance().addShape(newShape);
                SelectedShape.getInstance().setSelectedShape(newShape);
                InformationTextProvider.getInformationTextProperty().set(SHAPE_CREATION_SUCCESS);
                stage.close();
            }
            else InformationTextProvider.getInformationTextProperty().set(SHAPE_NAME_TAKEN);
        }
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
