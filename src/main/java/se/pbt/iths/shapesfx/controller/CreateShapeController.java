package se.pbt.iths.shapesfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.model.shapes.MyCircle;
import se.pbt.iths.shapesfx.model.shapes.MySquare;
import se.pbt.iths.shapesfx.model.shapes.MyTriangle;
import se.pbt.iths.shapesfx.modelmanagement.SavedShapes;
import se.pbt.iths.shapesfx.modelmanagement.SelectedShape;

/**
 * Controller class for the shape creation dialog window.
 * Handles user interactions and shape creation based on user input.
 */
public class CreateShapeController {

    private double size;
    private Paint paint;

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
    }

    /**
     * Handles the confirm shape button click event.
     * Creates the selected shape based on the dialog title, sets it as the selected shape in {@link SelectedShape}
     * and adds it to the {@link SavedShapes} list.
     */
    @FXML
    private void confirmShapeButtonClicked() {
        Shape shape;
        Stage stage = (Stage) sizeSlider.getScene().getWindow();
        var action = stage.getTitle();
        switch (action) {
            case "Create Circle" -> shape = new MyCircle(size, paint);
            case "Create Square" -> shape = new MySquare(size, paint);
            case "Create Triangle" -> shape = new MyTriangle(size, paint);
            default -> throw new IllegalArgumentException("Error while creating shape. No shape was created");
        }
        SelectedShape.getInstance().setSelectedShape(shape);
        SavedShapes.getInstance().addShape(shape);
        stage.close();
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
