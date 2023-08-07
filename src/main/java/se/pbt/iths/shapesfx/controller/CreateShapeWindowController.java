package se.pbt.iths.shapesfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.models.Circle;
import se.pbt.iths.shapesfx.models.ShapeTemplate;
import se.pbt.iths.shapesfx.models.Square;
import se.pbt.iths.shapesfx.models.Triangle;
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapeStorage;
import se.pbt.iths.shapesfx.modelsmanagement.SelectedShape;
import se.pbt.iths.shapesfx.utils.InformationTextProvider;

// TODO: Add builder/factory pattern
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
    private Paint paint;

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
        paint = colorPicker.getValue();

        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> size = newValue.doubleValue());
        colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> paint = newValue);
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
            var action = stage.getTitle();
            var duplicateName = DrawnShapeStorage.getInstance().getByName(name);
            if (duplicateName.isEmpty()) {
                switch (action) {
                    case "Circle" -> newShape = new Circle(name, paint, size);
                    case "Square" -> newShape = new Square(name, paint, size);
                    case "Triangle" -> newShape = new Triangle(name, paint, size);
                    default -> throw new IllegalArgumentException("Error while creating shape. No shape was created");
                }
                DrawnShapeStorage.getInstance().addShape(newShape);
                SelectedShape.getInstance().setSelectedShape(newShape);
                stage.close();
                InformationTextProvider.getInformationTextProperty().set(SHAPE_CREATION_SUCCESS);
            }
            else InformationTextProvider.getInformationTextProperty().set(SHAPE_NAME_TAKEN);
        }
    }


    /**
     * Check if all the required fields have valid values
     * @return boolean indicating whether all fields are valid
     */
    private boolean allFieldsHaveValues() {
        if (shapeNameField == null || paint == null)
            return false;

        return shapeNameField.getText().length() > 0
                && size > 0.0;
    }
}
