package se.pbt.iths.shapesfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.interfaces.Drawable;
import se.pbt.iths.shapesfx.models.MyCircle;
import se.pbt.iths.shapesfx.models.MySquare;
import se.pbt.iths.shapesfx.models.MyTriangle;
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapesMenu;
import se.pbt.iths.shapesfx.modelsmanagement.SelectedShape;
import se.pbt.iths.shapesfx.utils.InformationTextProvider;

/**
 * Controller class for the shape creation dialog window.
 * Handles user interactions and shape creation based on user input.
 */
public class CreateShapeController {

    private double size;
    private Paint paint;

    @FXML
    public TextField shapeNameField;
    @FXML
    private Slider sizeSlider;
    @FXML
    private ColorPicker colorPicker;


    /**
     * Initializes the controller and sets up event listeners for sizeSlider and colorPicker,
     * then retrieves the initial values of size and paint.
     */
    @FXML
    private void initialize() {
        size = sizeSlider.getValue();
        paint = colorPicker.getValue();

        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> size = newValue.doubleValue());
        colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> paint = newValue);
        InformationTextProvider.getInformationTextProperty().set("Set the size and color of your shape and press 'Confirm'.");
    }

    /**
     * Handles the confirm shape button click event.
     * Creates the selected shape based on the dialog title, sets it as the selected shape in {@link SelectedShape}
     * and adds it to the {@link DrawnShapesMenu} list.
     */
    @FXML
    private void confirmShapeButtonClicked() {
        if (!allFieldsHaveValues()) {
            InformationTextProvider.getInformationTextProperty().set("All of the shape's properties must have a value");
        } else {
            Drawable shape;
            Stage stage = (Stage) sizeSlider.getScene().getWindow();
            var action = stage.getTitle();
            switch (action) {
                case "Create Circle" -> shape = new MyCircle(shapeNameField.getText(), size, paint);
                case "Create Square" -> shape = new MySquare(shapeNameField.getText(), size, paint);
                case "Create Triangle" -> shape = new MyTriangle(shapeNameField.getText(),  size, paint);
                default -> throw new IllegalArgumentException("Error while creating shape. No shape was created");
            }
            SelectedShape.getInstance().setSelectedShape(shape);
            stage.close();
            InformationTextProvider.getInformationTextProperty().set("Click on the canvas to add your shape.");
        }
    }


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
