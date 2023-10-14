package se.pbt.iths.doodledo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import se.pbt.iths.doodledo.models.shapes.ShapeTemplate;
import se.pbt.iths.doodledo.modelsmanagement.SelectedShape;
import se.pbt.iths.doodledo.ui.resources.AppMessages;
import se.pbt.iths.doodledo.ui.utils.InformationTextProvider;

/**
 * Controller class for the shape edit dialog window.
 * Handles user interactions and shape editing based on user input.
 */
public class EditShapeWindowController {

    // Non-final private instance fields
    private double newSize;
    private Color newColor;

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
        var shapeToEdit = SelectedShape.getInstance().getSelectedShape();

        setDefaultValues(shapeToEdit);

        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> newSize = newValue.doubleValue());
        colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> newColor = newValue);

        setInformationText(AppMessages.INITIALIZE_SHAPE_MESSAGE);
    }

    /**
     * Sets the default values for the user input controls based on the properties of the specified shape.
     *
     * @param shapeToEdit The shape whose properties will be used to set the default values. Must not be null.
     */
    private void setDefaultValues(ShapeTemplate shapeToEdit) {
        newSize = shapeToEdit.getSize();
        newColor = (Color) shapeToEdit.getPaint();

        shapeNameField.setText(shapeToEdit.getName());
        colorPicker.setValue(newColor);
        sizeSlider.setValue(newSize);
    }


    /**
     * Applies changes to the selected shape based on user input when the "Confirm Changes" button is clicked.
     * If any required field is missing, an error message is displayed. Otherwise, the shape's properties are
     * updated, a success message is displayed, and the current window is closed.
     */
    @FXML
    private void confirmChangesButtonClicked() {
        if (!allFieldsHaveValues()) {
            setInformationText(AppMessages.SHAPE_PROPERTIES_REQUIRED);
        } else {
            try {
                updateShapeProperties();
                setInformationText(AppMessages.IMPROVEMENT_MESSAGE);
                closeCurrentWindow();
            } catch (RuntimeException runtimeException) {
                setInformationText(AppMessages.SORRY_MESSAGE);
                runtimeException.printStackTrace();
            }
        }
    }

    /**
     * Updates the properties of the currently selected shape with values from the user input fields.
     */
    private void updateShapeProperties() {
        var shape = SelectedShape.getInstance().getSelectedShape();
        shape.setName(shapeNameField.getText());
        shape.setPaint(newColor);
        shape.setSize(newSize);
    }

    /**
     * Closes the current window (stage).
     */
    private void closeCurrentWindow() {
        var stage = (Stage) sizeSlider.getScene().getWindow();
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
