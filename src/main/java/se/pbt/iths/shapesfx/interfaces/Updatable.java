package se.pbt.iths.shapesfx.interfaces;

import se.pbt.iths.shapesfx.models.shapes.ShapeTemplate;

@FunctionalInterface
public interface Updatable {
    void update(ShapeTemplate updatingValues);
}
