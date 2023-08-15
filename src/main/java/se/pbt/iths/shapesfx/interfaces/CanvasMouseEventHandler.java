package se.pbt.iths.shapesfx.interfaces;

import javafx.scene.input.MouseEvent;

@FunctionalInterface
public interface CanvasMouseEventHandler {
    void handle(MouseEvent event);
}
