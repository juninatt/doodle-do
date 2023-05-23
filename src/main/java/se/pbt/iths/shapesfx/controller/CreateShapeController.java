package se.pbt.iths.shapesfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.model.MyCircle;
import se.pbt.iths.shapesfx.model.MySquare;
import se.pbt.iths.shapesfx.model.MyTriangle;
import se.pbt.iths.shapesfx.modelmanagement.SavedShapes;

public class CreateShapeController {

    private Stage stage;
    private double size;
    private Paint paint;

    @FXML
    private Slider sizeSlider;
    @FXML
    private ColorPicker colorPicker;


    @FXML
    private void initialize() {
        size = sizeSlider.getValue();
        paint = colorPicker.getValue();

        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> size = newValue.doubleValue());
        colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> paint = newValue);
    }

    @FXML
    private void handleConfirmCircleButton() {
        var shape = stage.getTitle().toLowerCase();
        if (shape.contains("circle")) {
            MyCircle circle = new MyCircle(size, paint);
            circle.setSelected(true);
            SavedShapes.getInstance().addCircle(circle);
        } else if (shape.contains("square")) {
            MySquare square = new MySquare(size, paint);
            square.setSelected(true);
            SavedShapes.getInstance().addSquare(square);
        } else if (shape.contains("triangle")) {
            MyTriangle triangle = new MyTriangle(size, paint);
            triangle.setSelected(true);
            SavedShapes.getInstance().addTriangle(triangle);
        }
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
