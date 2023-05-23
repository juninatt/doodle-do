package se.pbt.iths.shapesfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import se.pbt.iths.shapesfx.model.MyCircle;
import se.pbt.iths.shapesfx.model.MySquare;
import se.pbt.iths.shapesfx.model.MyTriangle;
import se.pbt.iths.shapesfx.modelmanagement.SavedShapes;
import se.pbt.iths.shapesfx.modelmanagement.SelectedShape;

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
    private void handleConfirmShapeButton() {
        Shape shape;
        var shapeType = stage.getTitle().toLowerCase();
        if (shapeType.contains("circle")) {
            shape = new MyCircle(size, paint);
            SavedShapes.getInstance().addShape(shape);
        } else if (shapeType.contains("square")) {
            shape = new MySquare(size, paint);
        } else if (shapeType.contains("triangle")) {
            shape = new MyTriangle(size, paint);
        } else {
            throw new IllegalArgumentException("Error while creating shape. No shape was created");
        }
        SelectedShape.getInstance().setSelectedShape(shape);
        SavedShapes.getInstance().addShape(shape);
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
