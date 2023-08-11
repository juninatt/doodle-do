package se.pbt.iths.shapesfx.operations;

import se.pbt.iths.shapesfx.exceptions.ShapeSaveException;
import se.pbt.iths.shapesfx.models.ShapeTemplate;
import javafx.stage.FileChooser;
import se.pbt.iths.shapesfx.ui.resources.AppMessages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Provides functionality to save shapes in SVG format.
 */
public class ShapeSaver {

    /**
     * Attempts to save the provided shape as an SVG file.
     *
     * @param shape The shape to save.
     * @return true if saved successfully, false otherwise.
     * @throws ShapeSaveException if there is an error during the saving process.
     */
    public boolean saveShapeAsSVG(ShapeTemplate shape) throws ShapeSaveException {
        File file = showSaveFileDialog();

        if (file != null) {
            return writeToFile(shape, file);
        }
        return false;
    }

    /**
     * Prompts the user to choose a location and name for the file.
     *
     * @return The selected file, or null if the user cancelled.
     */
    private File showSaveFileDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Shape as SVG");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVG Files", "*.svg"));
        return fileChooser.showSaveDialog(null);
    }

    /**
     * Writes the shape's SVG path to the specified file.
     *
     * @param shape The shape to save.
     * @param file The target file.
     * @return true if written successfully, false otherwise.
     * @throws ShapeSaveException if there is an error during the write process.
     */
    private boolean writeToFile(ShapeTemplate shape, File file) throws ShapeSaveException {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(shape.toSvgPath());
            return true;
        } catch (IOException ioException) {
            throw new ShapeSaveException(AppMessages.SVG_SAVE_ERROR_MSG, ioException);
        }
    }
}

