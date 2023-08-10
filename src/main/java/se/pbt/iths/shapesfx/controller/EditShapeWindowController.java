package se.pbt.iths.shapesfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.models.ShapeTemplate;
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapeStorage;
import se.pbt.iths.shapesfx.modelsmanagement.SelectedShape;
import se.pbt.iths.shapesfx.ui.utils.InformationTextProvider;

// TODO: Improve exception handling
// TODO: Handle name duplication
/**
 * Controller class for the shape edit dialog window.
 * Handles user interactions and shape editing based on user input.
 */
public class EditShapeWindowController {

    private static final String INITIALIZE_SHAPE_MESSAGE = "Make the desired changes to your shape. Press the button when you are done!";
    private static final String SHAPE_PROPERTIES_REQUIRED = "All of the shape's properties must have a value";
    private static final String IMPROVEMENT_MESSAGE = "What an improvement!";
    private static final String SORRY_MESSAGE = "Ooops! Something seems to have gone wrong there. Sorry about that please try again";

    private String newName;
    private double newSize;
    private Color newColor;

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
        var shapeToEdit = SelectedShape.getInstance().getSelectedShape();

        setDefaultValues(shapeToEdit);

        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> newSize = newValue.doubleValue());
        colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> newColor = newValue);

        setInformationText(INITIALIZE_SHAPE_MESSAGE);
    }

    /**
     * Sets default values to shape properties.
     *
     * @param shapeToEdit the shape to be edited
     */
    private void setDefaultValues(ShapeTemplate shapeToEdit) {
        newSize = shapeToEdit.getSize();
        newColor = (Color) shapeToEdit.getPaint();
        newName = shapeToEdit.getName();

        shapeNameField.setText(newName);
        colorPicker.setValue(newColor);
        sizeSlider.setValue(newSize);
    }


    /**
     * Handles the confirm shape button click event.
     * Updates the selected shape based on user input.
     */
    @FXML
    private void confirmChangesButtonClicked() {
        if (!allFieldsHaveValues()) {
            setInformationText(SHAPE_PROPERTIES_REQUIRED);
        } else {
            try {
                DrawnShapeStorage.getInstance().getByName(SelectedShape.getInstance().getName())
                        .ifPresent(shape -> shape.update(newName, newColor, newSize));

                setInformationText(IMPROVEMENT_MESSAGE);
                closeCurrentWindow();
            } catch (RuntimeException runtimeException) {
                setInformationText(SORRY_MESSAGE);
                runtimeException.printStackTrace();
            }
        }
    }

    /**
     * Closes the current window.
     */
    private void closeCurrentWindow() {
        Stage stage = (Stage) sizeSlider.getScene().getWindow();
        stage.close();
    }

    /**
     * Checks if all the required fields have valid values
     *
     * @return boolean indicating whether all fields are valid
     */
    private boolean allFieldsHaveValues() {
        if (shapeNameField == null || newColor == null)
            return false;

        return shapeNameField.getText().length() > 0
                && newSize > 0.0;
    }

    /**
     * Updates the information text with the provided message.
     *
     * @param message the message to display
     */
    private void setInformationText(String message) {
        InformationTextProvider.getMessage().set(message);
    }
}
