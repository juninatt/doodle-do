package se.pbt.iths.doodledo.interfaces;

import se.pbt.iths.doodledo.models.shapes.ShapeTemplate;

@FunctionalInterface
public interface Updatable {
    void update(ShapeTemplate updatingValues);
}
