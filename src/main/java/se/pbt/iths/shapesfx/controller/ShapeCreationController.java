package se.pbt.iths.shapesfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.model.MyCircle;

public class ShapeCreationController {

    private Stage stage;
    private double radius;
    private Color color;

    @FXML
    private Slider sizeSlider;
    @FXML
    private ColorPicker colorPicker;


    @FXML
    private void initialize() {
        // Set the initial values for size and color
        radius = sizeSlider.getValue();
        color = colorPicker.getValue();

        // Listen for changes in size and color
        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> radius = newValue.doubleValue());

        colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> color = newValue);
    }

    @FXML
    private void handleConfirmCircleButton() {
        // Create a circle object with the selected size and color
        MyCircle circle = new MyCircle(radius, color);


        // Close the window
        stage.close();

        // Perform any additional actions with the created circle object
        // For example, you could pass it to another method or update the canvas with the circle
        // ...
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
