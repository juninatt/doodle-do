package se.pbt.iths.shapesfx.models.shapes;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import se.pbt.iths.shapesfx.models.shapes.polygonal.Triangle;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Triangle:")
class TriangleTest {

    @Nested
    @DisplayName("contains method:")
    class ContainsTests {

        @Test
        @DisplayName("Returns true when point is outside inside")
        void pointContained() {
            var triangle = new Triangle("MyTriangle", Color.RED, 10);
            triangle.calculateVertices(5, 5); // Center coordinates

            assertTrue(triangle.contains(5, 2)); // Point within the triangle
        }

        @Test
        @DisplayName("Returns false when point is outside triangle")
        void pointOutside() {
            var triangle = new Triangle("MyTriangle", Color.RED, 10);
            triangle.calculateVertices(5, 5); // Center coordinates

            assertFalse(triangle.contains(10, 5)); // Point outside the triangle
        }
    }

    @Nested
    @DisplayName("clone method:")
    class CloneTests {

        @Test
        @DisplayName("Creates identical object when cloned")
        void cloneTriangle() {
            var triangle = new Triangle("MyTriangle", Color.RED, 10);
            triangle.calculateVertices(5, 5); // Center coordinates
            var clonedTriangle = triangle.clone();

            assertEquals(triangle.getName(), clonedTriangle.getName());
            assertEquals(triangle.getSize(), clonedTriangle.getSize(), 0.001);
            assertArrayEquals(triangle.getVertices(), clonedTriangle.getVertices());
        }
    }

    @Nested
    @DisplayName("getHeight method:")
    class GetHeightTests {

        @Test
        @DisplayName("Calculates and returns height of the triangle")
        void calculateHeight() {
            var triangle = new Triangle("MyTriangle", Color.RED, 6);
            double height = triangle.getHeight();

            assertEquals(height, 5.1962, 0.001);
        }
    }
}
