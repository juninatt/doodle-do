package se.pbt.iths.shapesfx.interfaces;

import javafx.scene.input.MouseEvent;

public interface CanvasCommand {
    void execute(MouseEvent event);
    void undo();
    void redo();
}
