package se.pbt.iths.shapesfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.model.MyCircle;
import se.pbt.iths.shapesfx.modelmanagement.SavedShapes;

public class ShapeCreationController {

    private Stage stage;
    private double radius;
    private Paint paint;

    @FXML
    private Slider sizeSlider;
    @FXML
    private ColorPicker colorPicker;


    @FXML
    private void initialize() {
        radius = sizeSlider.getValue();
        paint = colorPicker.getValue();

        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> radius = newValue.doubleValue());
        colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> paint = newValue);
    }

    @FXML
    private void handleConfirmCircleButton() {
        MyCircle circle = new MyCircle(radius, paint);
        circle.setSelected(true);
        SavedShapes.getInstance().getSavedCircles().add(circle);

        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
