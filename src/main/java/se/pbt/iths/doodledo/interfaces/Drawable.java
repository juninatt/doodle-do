package se.pbt.iths.doodledo.interfaces;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

@FunctionalInterface
public interface Drawable {
    void draw(GraphicsContext gc, Point2D center);
}

