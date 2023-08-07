package se.pbt.iths.shapesfx.modelsmanagement;

import javafx.scene.shape.Shape;
import se.pbt.iths.shapesfx.models.ShapeTemplate;

import java.util.ArrayList;
import java.util.List;

// TODO: Implement
/**
 * Class for holding a cache of shapes in SVG format.
 */
public class CachedShapeStorage {

    private List<ShapeTemplate> shapeCache;

    /**
     * Constructs a new ShapeCache.
     */
    public CachedShapeStorage() {
        shapeCache = new ArrayList<>();
    }

    /**
     * Adds a shape to the cache.
     *
     * @param shape The shape to add.
     */
    public void addShape(ShapeTemplate shape) {
        shapeCache.add(shape);
    }

    /**
     * Removes a shape from the cache.
     *
     * @param shape The shape to remove.
     */
    public void removeShape(ShapeTemplate shape) {
        shapeCache.remove(shape);
    }

    /**
     * Returns a list of all shapes in the cache.
     *
     * @return A list of shapes.
     */
    public List<Shape> getShapes() {
        return new ArrayList<>(shapeCache);
    }

    /**
     * Clears the cache of shapes.
     */
    public void clear() {
        shapeCache.clear();
    }
}
