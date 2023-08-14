package se.pbt.iths.shapesfx.interfaces;

import se.pbt.iths.shapesfx.utils.ShapeRotator;

public interface Rotatable {
    void rotate(ShapeRotator rotator, double angle);

    int ROW_X = 0;
    int ROW_Y = 1;
}

