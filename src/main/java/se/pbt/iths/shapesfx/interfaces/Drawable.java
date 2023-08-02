package se.pbt.iths.shapesfx.interfaces;

import javafx.scene.canvas.GraphicsContext;

public interface Drawable extends ShapeProperties {
    void draw(GraphicsContext gc, double x, double y);
}

