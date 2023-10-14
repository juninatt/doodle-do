package se.pbt.iths.doodledo.models.shapes;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import se.pbt.iths.doodledo.models.shapes.nonpolygonal.Circle;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Circle tests:")
class CircleTest {

    private Circle circle;

    @BeforeEach
    void setUp() {
        circle = new Circle("testCircle", Color.RED, 10.0, new Point2D(5.0, 5.0));
    }

    @Nested
    @DisplayName("toSvgPath method:")
    class ToSvgPathTests {

        @Test
        @DisplayName("Creates correct SVG path for circle 1.")
        void createsCorrectSvgPath() {
            String expectedPath = "M 5.000000 5.000000 m -5.000000, 0 a 5.000000,5.000000 0 1,0 10.000000,0 a 5.000000,5.000000 0 1,0 -10.000000,0";
            assertEquals(expectedPath, circle.toSvgPath());
        }


        @Test
        @DisplayName("Creates correct SVG path for circle 2.")
        void createsCorrectSvgPathDifferentRadius() {
            Circle circle = new Circle("radiusCircle", Color.YELLOW, 15.0, new Point2D(10.0, 10.0));
            String expectedPath = "M 10.000000 10.000000 m -7.500000, 0 a 7.500000,7.500000 0 1,0 15.000000,0 a 7.500000,7.500000 0 1,0 -15.000000,0";
            assertEquals(expectedPath, circle.toSvgPath());
        }

    }

    @Nested
    @DisplayName("contains method:")
    class ContainsTests {

        @Test
        @DisplayName("Returns true when point is inside the circle")
        void whenPointInsideCircle_returnsTrue() {
            assertTrue(circle.contains(new Point2D(5.0, 5.0)));
        }

        @Test
        @DisplayName("Returns false when point is outside the circle")
        void whenPointOutsideCircle_returnsFalse() {
            assertFalse(circle.contains(new Point2D(10.0, 10.0)));
        }
    }

    @Nested
    @DisplayName("clone method:")
    class CloneTests {

        @Test
        @DisplayName("Creates identical object when cloned")
        void whenCalled_createsIdenticalObject() {
            var clone = circle.clone();

            assertEquals(circle.getName(), clone.getName());
            assertEquals(circle.getSize(), clone.getSize());
            assertEquals(circle.getPaint(), clone.getPaint());
            assertEquals(circle.getSize(), clone.getHeight());
        }
    }

    @Nested
    @DisplayName("getHeight method:")
    class GetHeightTests {

        @Test
        @DisplayName("Returns circle size when getting height")
        void whenCalled_returnsCircleSize() {
            assertEquals(10.0, circle.getSize());
        }
    }
}
