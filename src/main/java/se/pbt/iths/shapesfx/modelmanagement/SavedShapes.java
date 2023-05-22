package se.pbt.iths.shapesfx.modelmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.pbt.iths.shapesfx.model.MyCircle;
import se.pbt.iths.shapesfx.model.MyTriangle;

public class SavedShapes {

    public static SavedShapes instance;

    private final ObservableList<MyCircle> savedCircles;

    private final ObservableList<MyTriangle> savedTriangles;

    public SavedShapes() {
        this.savedCircles = FXCollections.observableArrayList();
        this.savedTriangles = FXCollections.observableArrayList();
    }

    public static SavedShapes getInstance() {
        if (instance == null) {
            instance = new SavedShapes();
        }
        return instance;
    }

    public void addCircle(MyCircle circle) {
        if (circle != null) {
            savedCircles.add(circle);
        } else {
            throw new IllegalArgumentException("Circle cannot be null");
        }
    }

    public void addTriangle(MyTriangle triangle) {
        if (triangle != null) {
            savedTriangles.add(triangle);
        } else {
            throw new IllegalArgumentException("Triangle cannot be null");
        }
    }

    public void removeCircle(MyCircle circle) {
        if (circle != null) {
            savedCircles.remove(circle);
        } else {
            throw new IllegalArgumentException("Circle cannot be null");
        }
    }

    public void removeTriangle(MyTriangle triangle) {
        if (triangle != null) {
            savedTriangles.remove(triangle);
        } else {
            throw new IllegalArgumentException("Triangle cannot be null");
        }
    }

    public ObservableList<MyCircle> getSavedCircles() {
        return savedCircles;
    }
}
