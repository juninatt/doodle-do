package se.pbt.iths.shapesfx.modelmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.pbt.iths.shapesfx.model.MyCircle;

public class SavedShapes {

    public static SavedShapes instance;

    private final ObservableList<MyCircle> savedCircles;

    public SavedShapes() {
        this.savedCircles = FXCollections.observableArrayList();

    }

    public static SavedShapes getInstance() {
        if (instance == null) {
            instance = new SavedShapes();
        }
        return instance;
    }

    public void add(MyCircle circle) {
        if (circle != null) {
            savedCircles.add(circle);
        } else {
            throw new IllegalArgumentException("Circle cannot be null");
        }
    }

    public void remove(MyCircle circle) {
        if (circle != null) {
            savedCircles.remove(circle);
        } else {
            throw new IllegalArgumentException("Circle cannot be null");
        }
    }

    public ObservableList<MyCircle> getSavedCircles() {
        return savedCircles;
    }
}
