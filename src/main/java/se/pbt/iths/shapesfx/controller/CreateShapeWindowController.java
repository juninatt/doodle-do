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
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapesMenu;
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
        if (!allFieldsHaveValues()) {
            InformationTextProvider.getInformationTextProperty().set(SHAPE_PROPERTIES_REQUIRED);
        } else {
            ShapeTemplate shape;
            Stage stage = (Stage) sizeSlider.getScene().getWindow();
            var action = stage.getTitle();
            switch (action) {
                case "Circle" -> shape = new Circle(shapeNameField.getText(), paint, size);
                case "Square" -> shape = new Square(shapeNameField.getText(), paint, size);
                case "Triangle" -> shape = new Triangle(shapeNameField.getText(), paint, size);
                default -> throw new IllegalArgumentException("Error while creating shape. No shape was created");
            }
            DrawnShapesMenu.getInstance().addShape(shape);
            SelectedShape.getInstance().setSelectedShape(shape);
            stage.close();
            InformationTextProvider.getInformationTextProperty().set(SHAPE_CREATION_SUCCESS );
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


    public double getSize() {
        return size;
    }

    public Paint getPaint() {
        return paint;
    }

    public Slider getSizeSlider() {
        return sizeSlider;
    }

    public ColorPicker getColorPicker() {
        return colorPicker;
    }
}
