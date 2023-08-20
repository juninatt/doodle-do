package se.pbt.iths.shapesfx.models.shapes;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import se.pbt.iths.shapesfx.models.shapes.polygonal.Square;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Square tests:")
class SquareTest {

    @Nested
    @DisplayName("contains method:")
    class ContainsTests {

        @Test
        @DisplayName("Returns true when point is outside square")
        void pointContained() {
            Square square = new Square("MySquare", Color.BLUE, 10);
            square.calculateVertices(5, 5); // Center coordinates

            assertTrue(square.contains(3, 3)); // Point within the square
        }

        @Test
        @DisplayName("Returns false when point is outside square")
        void pointOutside() {
            Square square = new Square("MySquare", Color.BLUE, 10);
            square.calculateVertices(5, 5); // Center coordinates

            assertFalse(square.contains(11, 5)); // Point outside the square
        }
    }

    @Nested
    @DisplayName("calculateVertices method:")
    class CalculateVerticesTests {

        @Test
        @DisplayName("Correctly calculates vertices positions for the square")
        void calculateVerticesPositions() {
            Square square = new Square("MySquare", Color.BLUE, 4);
            square.calculateVertices(5, 5); // Center coordinates

            double[][] vertices = square.getVertices();

            assertArrayEquals(new double[]{3, 7, 7, 3}, vertices[Square.ROW_X], 0.001);
            assertArrayEquals(new double[]{3, 3, 7, 7}, vertices[Square.ROW_Y], 0.001);
        }
    }

    @Nested
    @DisplayName("clone method:")
    class CloneTests {

        @Test
        @DisplayName("Creates identical object when cloned")
        void cloneSquare() {
            Square square = new Square("MySquare", Color.BLUE, 10);
            square.calculateVertices(5, 5); // Center coordinates
            Square clonedSquare = square.clone();

            assertEquals(square.getName(), clonedSquare.getName());
            assertEquals(square.getSize(), clonedSquare.getSize(), 0.001);
            assertArrayEquals(square.getVertices(), clonedSquare.getVertices());
        }
    }
}
