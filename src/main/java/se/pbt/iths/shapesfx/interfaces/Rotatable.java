package se.pbt.iths.shapesfx.interfaces;

import se.pbt.iths.shapesfx.utils.ShapeRotator;

@FunctionalInterface
public interface Rotatable {
    void rotate(ShapeRotator rotator, double angle);
}

