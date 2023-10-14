package se.pbt.iths.doodledo.interfaces;

import javafx.scene.canvas.GraphicsContext;

@FunctionalInterface
public interface Drawable {
    void draw(GraphicsContext gc, double x, double y);
}

