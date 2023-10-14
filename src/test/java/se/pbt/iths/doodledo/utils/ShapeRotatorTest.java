package se.pbt.iths.doodledo.utils;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ShapeRotator tests:")
class ShapeRotatorTest {

    static ShapeRotator shapeRotator;
    @BeforeAll
    static void init() {
        shapeRotator = new ShapeRotator();
    }

    @Test
    @DisplayName("Points rotate by 90 degrees around its center")
    void testRotatePoints_90Degrees() {
        double[][] vertices = {{0, 2, 2, 0}, {0, 0, 2, 2}};
        double[][] expected = {{0, 0, -2, -2}, {0, 2, 2, 0}};
        double[][] result = shapeRotator.rotatePoints(vertices, new Point2D(0, 0), 90);

        assertEqualValues(expected, result);
    }

    @Test
    @DisplayName("Points rotate by 180 degrees around a custom center point (3, 3)")
    void testRotatePoints_180Degrees_CustomCenter() {
        double[][] vertices = {{3, 4}, {3, 4}};
        double[][] expected = {{3, 2}, {3, 2}};
        double[][] result = shapeRotator.rotatePoints(vertices, new Point2D(3, 3), 180);

        assertEqualValues(expected, result);
    }

    @Test
    @DisplayName("Points dont rotate when angle is 0 degrees")
    void testRotatePoints_NoRotation() {
        double[][] vertices = {{3, 5}, {3, 5}};
        double[][] expected = {{3, 5}, {3, 5}};
        double[][] result = shapeRotator.rotatePoints(vertices, new Point2D(3, 3), 0);

        assertEqualValues(expected, result);
    }

    @Test
    @DisplayName("Rotates triangle by 90 degrees around its center")
    void testRotateTriangle_90Degrees() {
        double[][] vertices = {{0, 1, 0}, {0, 0, 1}}; // Triangle vertices
        double[][] expected = {{0, 0, -1}, {0, 1, 0}};
        double[][] result = shapeRotator.rotatePoints(vertices, new Point2D(0, 0), 90);

        assertEqualValues(expected, result);
    }

    @Test
    @DisplayName("Rotates triangle by 180 degrees around its center")
    void testRotateTriangle_180Degrees_Centroid() {
        double[][] vertices = {{0, 1, 2}, {0, 2, 0}}; // Triangle vertices
        double[][] expected = {{2, 1, 0}, {0, -2, 0}};
        double[][] result = shapeRotator.rotatePoints(vertices, new Point2D(1, 0), 180);

        assertEqualValues(expected, result);
    }

    @Test
    @DisplayName("Rotates square by 180 degrees around its center")
    void testRotateSquare_180Degrees_Center() {
        double[][] vertices = {{0, 0, 1, 1}, {0, 1, 1, 0}}; // Square vertices
        double[][] expected = {{1, 1, 0, 0}, {1, 0, 0, 1}};
        double[][] result = shapeRotator.rotatePoints(vertices, new Point2D(0.5, 0.5), 180);

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
