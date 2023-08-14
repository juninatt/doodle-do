package se.pbt.iths.shapesfx.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShapeRotatorTest {

    @Test
    @DisplayName("Verify rotation of points by 90 degrees around the origin")
    void testRotatePoints_90Degrees() {
        ShapeRotator shapeRotator = new ShapeRotator();
        double[][] vertices = {{0, 2, 2, 0}, {0, 0, 2, 2}};
        double[][] expected = {{0, 0, -2, -2}, {0, 2, 2, 0}};
        double[][] result = shapeRotator.rotatePoints(vertices, 0, 0, 90);

        assertEqualValues(expected, result);
    }

    @Test
    @DisplayName("Verify rotation of points by 180 degrees around a custom center point (3, 3)")
    void testRotatePoints_180Degrees_CustomCenter() {
        ShapeRotator shapeRotator = new ShapeRotator();
        double[][] vertices = {{3, 4}, {3, 4}};
        double[][] expected = {{3, 2}, {3, 2}};
        double[][] result = shapeRotator.rotatePoints(vertices, 3, 3, 180);

        assertEqualValues(expected, result);
    }

    @Test
    @DisplayName("Verify no rotation when angle is 0 degrees")
    void testRotatePoints_NoRotation() {
        ShapeRotator shapeRotator = new ShapeRotator();
        double[][] vertices = {{3, 5}, {3, 5}};
        double[][] expected = {{3, 5}, {3, 5}};
        double[][] result = shapeRotator.rotatePoints(vertices, 3, 3, 0);

        assertEqualValues(expected, result);
    }

    @Test
    @DisplayName("Verify rotation of a triangle by 90 degrees around the origin")
    void testRotateTriangle_90Degrees() {
        ShapeRotator shapeRotator = new ShapeRotator();
        double[][] vertices = {{0, 1, 0}, {0, 0, 1}}; // Triangle vertices
        double[][] expected = {{0, 0, -1}, {0, 1, 0}};
        double[][] result = shapeRotator.rotatePoints(vertices, 0, 0, 90);

        assertEqualValues(expected, result);
    }

    @Test
    @DisplayName("Verify rotation of a triangle by 180 degrees around its centroid")
    void testRotateTriangle_180Degrees_Centroid() {
        ShapeRotator shapeRotator = new ShapeRotator();
        double[][] vertices = {{0, 1, 2}, {0, 2, 0}}; // Triangle vertices
        double[][] expected = {{2, 1, 0}, {0, -2, 0}};
        double[][] result = shapeRotator.rotatePoints(vertices, 1, 0, 180);

        assertEqualValues(expected, result);
    }

    @Test
    @DisplayName("Verify rotation of a square by 180 degrees around its center")
    void testRotateSquare_180Degrees_Center() {
        ShapeRotator shapeRotator = new ShapeRotator();
        double[][] vertices = {{0, 0, 1, 1}, {0, 1, 1, 0}}; // Square vertices
        double[][] expected = {{1, 1, 0, 0}, {1, 0, 0, 1}};
        double[][] result = shapeRotator.rotatePoints(vertices, 0.5, 0.5, 180);

        assertEqualValues(expected, result);
    }


    /**
     * Private helper method to assert that two 2D arrays are equal, element by element.
     *
     * @param expected The expected 2D array.
     * @param result   The actual 2D array to be compared with the expected one.
     */
    private static void assertEqualValues(double[][] expected, double[][] result) {
        IntStream.range(0, expected.length)
                .forEach(i -> IntStream.range(0, expected[i].length)
                        .forEach(j -> assertEquals(expected[i][j], result[i][j], 1e-6)));
    }
}
