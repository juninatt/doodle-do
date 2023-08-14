package se.pbt.iths.shapesfx.interfaces;

import javafx.scene.paint.Paint;

@FunctionalInterface
public interface Updatable {
    void update(String name, Paint paint, double size);
}
