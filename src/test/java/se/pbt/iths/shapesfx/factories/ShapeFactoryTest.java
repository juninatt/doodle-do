package se.pbt.iths.shapesfx.factories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.pbt.iths.shapesfx.factory.ShapeFactory;
import se.pbt.iths.shapesfx.models.shapes.nonpolygonal.Circle;
import se.pbt.iths.shapesfx.models.shapes.polygonal.Square;
import se.pbt.iths.shapesfx.models.shapes.polygonal.Triangle;

@DisplayName("ShapeFactory tests:")
public class ShapeFactoryTest {

    @Test
    @DisplayName("Creates circle with correct values")
    void testCreateCircle() {
        var shape = ShapeFactory.createShape("circle", "MyCircle", Color.RED, 5.0);
        assertTrue(shape instanceof Circle);
        assertEquals("MyCircle", shape.getName());
        assertEquals(Color.RED, shape.getPaint());
        assertEquals(5.0, (shape).getSize());
    }

    @Test
    @DisplayName("Creates square with correct values")
    void testCreateSquare() {
        var shape = ShapeFactory.createShape("square", "MySquare", Color.GREEN, 4.0);
        assertTrue(shape instanceof Square);
        assertEquals("MySquare", shape.getName());
        assertEquals(Color.GREEN, shape.getPaint());
        assertEquals(4.0, (shape).getSize());
    }

    @Test
    @DisplayName("Creates triangle with correct values")
    void testCreateTriangle() {
        var shape = ShapeFactory.createShape("triangle", "MyTriangle", Color.BLUE, 3.0);
        assertTrue(shape instanceof Triangle);
        assertEquals("MyTriangle", shape.getName());
        assertEquals(Color.BLUE, shape.getPaint());
        assertEquals(3.0, (shape).getSize());
    }

    @Test
    @DisplayName("Throws illegal argument exception when type is invalid")
    void testCreateInvalidShape() {
        assertThrows(IllegalArgumentException.class, () ->
                ShapeFactory.createShape("hexagon", "MyHexagon", Color.YELLOW, 6.0)
        );
    }
}
